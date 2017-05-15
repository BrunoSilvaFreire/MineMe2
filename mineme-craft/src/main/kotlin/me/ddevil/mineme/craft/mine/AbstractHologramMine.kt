package me.ddevil.mineme.craft.mine

import com.google.common.collect.ImmutableMap
import com.sk89q.worldedit.regions.Region
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.api.hologram.HologramFormation
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.shiroi.craft.log.DebugLevel
import me.ddevil.shiroi.util.getOrException
import me.ddevil.util.getBoolean
import me.ddevil.util.getMapAny
import me.ddevil.util.getString
import org.bukkit.inventory.ItemStack
import java.util.*

abstract class AbstractHologramMine<R : Region> : AbstractMine<R>, HologramMine {

    final override var hologramsEnabled: Boolean
        set(value) {
            holograms.forEach {
                it.visible = value
            }
            field = value
        }
    final override var formation: HologramFormation?
        set(value) {
            internalHolograms.forEach { it.delete() }
            internalHolograms.clear()
            if (value != null) {
                internalHolograms.addAll(value.createHolograms(this))
            }
            field = value
        }

    final override val holograms: Set<Hologram>
        get() = HashSet(internalHolograms)

    private var internalHolograms: MutableSet<Hologram>


    @JvmOverloads
    constructor(plugin: MineMe,
                alias: String,
                name: String,
                composition: MineComposition,
                region: R,
                icon: ItemStack = plugin.mineManager.defaultMineIcon,
                defaultRepopulator: MineRepopulator = plugin.mineManager.defaultRepopulator,
                formation: HologramFormation? = null,
                resetDelay: Int = plugin.configManager.getValue(MineMeConfigValue.DEFAULT_MINE_RESET_DELAY)
    ) : super(plugin, alias, name, composition, region, icon, defaultRepopulator, resetDelay) {
        this.internalHolograms = HashSet()
        this.formation = formation
        if (formation != null) {
            internalHolograms.addAll(formation.createHolograms(this))
        }
        hologramsEnabled = false
    }

    constructor(plugin: MineMe, map: Map<String, Any>) : super(plugin, map) {
        val holo = plugin.hologramManager
        val holomap = map.getMapAny(MineMeConstants.MINE_HOLOGRAM_IDENTIFIER)
        internalHolograms = HashSet()
        this.hologramsEnabled = holomap.getBoolean(MineMeConstants.MINE_HOLOGRAM_ENABLED_IDENTIFIER)
        val formationName = holomap.getString(MineMeConstants.MINE_HOLOGRAM_FORMATION_IDENTIFIER)
        if (formationName == null) {
            formation = null
        } else {
            val tempFormation = holo.getFormation(formationName)
            if (tempFormation != null && hologramsEnabled) {
                internalHolograms.addAll(tempFormation.createHolograms(this))
            } else {
                plugin.pluginLogger.log("Couldn't find hologram formation '$formationName' while loading mine '$name', holograms will be disabled, please fix this mine's formation!", DebugLevel.FUCK_MAN_SOUND_THE_ALARMS)
            }
            formation = tempFormation
        }
    }

    override fun addHologram(hologram: Hologram) {
        internalHolograms.add(hologram)
    }

    override fun removeHologram(hologram: Hologram) {
        internalHolograms.remove(hologram)
    }

    override fun serialize(): Map<String, Any> {
        val holobuilder = ImmutableMap.builder<String, Any>()
                .put(MineMeConstants.MINE_HOLOGRAM_ENABLED_IDENTIFIER, enabled)
        val formation = formation
        if (formation != null) {
            holobuilder.put(MineMeConstants.MINE_HOLOGRAM_FORMATION_IDENTIFIER, formation.name)
        }
        return ImmutableMap.builder<String, Any>()
                .putAll(super.serialize())
                .put(MineMeConstants.MINE_HOLOGRAM_IDENTIFIER, holobuilder.build())
                .build()
    }
}