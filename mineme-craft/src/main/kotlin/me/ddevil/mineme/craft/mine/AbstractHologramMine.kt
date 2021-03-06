package me.ddevil.mineme.craft.mine

import com.sk89q.worldedit.regions.Region
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormation
import me.ddevil.mineme.craft.api.hologram.updater.HologramUpdater
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.hologram.misc.HologramConfig
import me.ddevil.shiroi.craft.log.DebugLevel
import me.ddevil.shiroi.craft.misc.variable.translateVariables
import me.ddevil.util.getMapAnyOrNull
import me.ddevil.util.immutableMap
import me.ddevil.util.set
import org.bukkit.inventory.ItemStack

abstract class AbstractHologramMine<R : Region> : AbstractMine<R>, HologramMine {

    final override var hologramsEnabled: Boolean
        set(value) {
            holograms.forEach {
                it.visible = value
            }
            field = value
        }

    final override var formation: HologramFormation
        set(value) {
            internalHolograms.forEach { it.delete() }
            internalHolograms.clear()
            if (plugin.hologramManager.hasHandler) {
                internalHolograms.addAll(value.createHolograms(this))
            }
            field = value
        }

    final override val holograms: Set<Hologram>
        get() = HashSet(internalHolograms)

    private var internalHolograms: MutableSet<Hologram>

    override var useCustomHologramText: Boolean
        set(value) {
            if (value != field) {
                field = value
            }
        }
    override var customHologramText: List<String>

    override val hologramText get() = if (useCustomHologramText) {
        customHologramText
    } else {
        plugin.configManager.getValue(MineMeConfigValue.DEFAULT_MINE_HOLOGRAM_TEXT)
    }

    private var internalUpdater: HologramUpdater

    override var hologramUpdater: HologramUpdater
        set(value) {
            internalUpdater.disable()
            internalUpdater = value
            value.enable()
        }
        get() = internalUpdater


    override fun updateHolograms() {
        for (hologram in holograms) {
            val lines = plugin.messageManager.translateAll(hologramText).map {
                translateVariables(it, *provide())
            }
            hologram.setLines(lines)
        }
    }

    @JvmOverloads
    constructor(plugin: MineMe,
                alias: String,
                name: String,
                composition: MineComposition,
                region: R,
                icon: ItemStack = plugin.mineManager.defaultMineIcon,
                defaultRepopulator: MineRepopulator = plugin.mineManager.defaultRepopulator,
                resetDelay: Int = plugin.configManager.getValue(MineMeConfigValue.DEFAULT_MINE_RESET_DELAY),
                formation: HologramFormation? = null,
                useCustomHologramText: Boolean = false,
                customHologramText: List<String> = emptyList()
    ) : super(plugin, alias, name, composition, region, icon, defaultRepopulator, resetDelay) {
        this.internalHolograms = HashSet()

        this.formation = formation ?: plugin.hologramManager.createDefaultFormation(this)
        this.internalHolograms.addAll(this.formation.createHolograms(this))
        this.hologramsEnabled = false
        this.useCustomHologramText = useCustomHologramText
        this.customHologramText = customHologramText
        this.internalUpdater = plugin.hologramManager.createDefaultUpdater(this)
    }

    constructor(plugin: MineMe, map: Map<String, Any>) : super(plugin, map) {
        val holoMap = map.getMapAnyOrNull(MineMeConstants.MINE_HOLOGRAM_IDENTIFIER)
        this.internalHolograms = HashSet()
        //Temporary variables
        var hologramsEnabled = false
        var useCustomHologramText = false
        var customHologramText = emptyList<String>()
        var updater = plugin.hologramManager.createDefaultUpdater(this)
        var formation = plugin.hologramManager.createDefaultFormation(this)

        if (holoMap != null) {
            //Load holo from config
            val holoConfig = HologramConfig(holoMap)
            val hologramManager = plugin.hologramManager


            hologramsEnabled = holoConfig.hologramsEnabled
            if (hologramsEnabled) {
                useCustomHologramText = holoConfig.useCustomHologramText
                customHologramText = holoConfig.customHologramText
                updater = plugin.hologramManager.loadUpdater(this,
                        holoConfig.hologramUpdaterMeta ?: emptyMap(),
                        holoConfig.hologramUpdaterName)
                //Load formation
                val formationName = holoConfig.formationName
                if (formationName != null) {
                    val tempFormation = hologramManager.getFormationLoader(formationName)
                    val formationMeta = holoConfig.formationMeta ?: emptyMap()
                    if (tempFormation != null) {
                        formation = tempFormation.loadFormation(this, formationMeta)
                    } else {
                        plugin.pluginLogger.log(
                                "Couldn't find hologram formation '$formationName' while loading mine" +
                                        " '$name', holograms will be disabled, please fix this mine's formation!",
                                DebugLevel.FUCK_MAN_SOUND_THE_ALARMS)
                        formation = hologramManager.createDefaultFormation(this)
                    }
                }
            }
        }
        this.useCustomHologramText = useCustomHologramText
        this.customHologramText = customHologramText
        this.hologramsEnabled = hologramsEnabled
        this.formation = formation
        this.internalUpdater = updater

        this.internalHolograms.addAll(formation.createHolograms(this))
    }

    override fun enable() {
        super.enable()
        internalUpdater.enable()
    }

    override fun disable() {
        super.disable()
        internalUpdater.disable()
        deleteHolograms()
    }

    private fun deleteHolograms() {
        holograms.forEach(Hologram::delete)
    }

    override fun addHologram(hologram: Hologram) {
        internalHolograms.add(hologram)
    }

    override fun removeHologram(hologram: Hologram) {
        internalHolograms.remove(hologram)
    }

    override fun serialize(): Map<String, Any> = immutableMap {
        putAll(super.serialize())
        this[MineMeConstants.MINE_HOLOGRAM_IDENTIFIER] = serializeHolograms()
    }

    private fun serializeHolograms() = immutableMap<String, Any> {
        this[MineMeConstants.MINE_HOLOGRAM_ENABLED_IDENTIFIER] = enabled
        this[MineMeConstants.MINE_HOLOGRAM_UPDATER_IDENTIFIER] = internalUpdater.serialize()
        this[MineMeConstants.MINE_HOLOGRAM_FORMATION_IDENTIFIER] = formation.serialize()
    }
}