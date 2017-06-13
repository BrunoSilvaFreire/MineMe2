package me.ddevil.mineme.craft.hologram

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.MineMeCraftConstants
import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.hologram.HologramProvider
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormationLoader
import me.ddevil.mineme.craft.api.hologram.updater.HologramUpdater
import me.ddevil.mineme.craft.api.hologram.updater.HologramUpdaterLoader
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.hologram.formation.CenterHologramFormation
import me.ddevil.mineme.craft.hologram.formation.loader.CenterHologramFormationLoader
import me.ddevil.mineme.craft.hologram.formation.loader.CustomHologramFormationLoader
import me.ddevil.mineme.craft.hologram.provider.holographicdisplays.HolographicDisplaysHandler
import me.ddevil.mineme.craft.hologram.updater.SyncHologramUpdater
import me.ddevil.mineme.craft.hologram.updater.loader.SyncHologramUpdaterLoader
import me.ddevil.shiroi.craft.log.DebugLevel
import org.bukkit.Bukkit
import org.bukkit.Location
import java.util.*

class InternalHologramManager
constructor(val plugin: MineMe) : HologramManager {


    private var handler: HologramHandler?
    private val formationLoaders: MutableSet<HologramFormationLoader>
    private var updaterLoaders: MutableList<HologramUpdaterLoader>

    init {
        this.formationLoaders = HashSet()
        this.updaterLoaders = ArrayList()
        this.handler = null
        if (plugin.configManager.getValue(MineMeConfigValue.HOLOGRAM_ENABLED))
            try {
                val value = plugin.configManager.getValue(MineMeConfigValue.HOLOGRAM_PROVIDER)
                val provider = HologramProvider.valueOf(value)
                when (provider) {
                    HologramProvider.HOLOGRAPHIC_DISPLAYS ->
                        if (Bukkit.getPluginManager().isPluginEnabled(MineMeCraftConstants.HOLOGRAPHIC_DISPLAYS_NAME)) {
                            handler = HolographicDisplaysHandler(plugin)
                        }
                }
            } catch(e: Exception) {
                throw IllegalStateException("There was an error while enabling ")
            }
        val h = handler
        if (h != null) {
            plugin.pluginLogger.log("Using $h as hologram provider!", DebugLevel.NO_BIG_DEAL)
        }
        registerDefaultFormations()
        registerDefaultUpdaterLoaders()
    }
    override fun createDefaultFormation(mine: HologramMine) = CenterHologramFormation(this)
    private fun registerDefaultUpdaterLoaders() {
        registerUpdaterLoader(SyncHologramUpdaterLoader(plugin))
    }

    private fun registerDefaultFormations() {
        registerFormationLoader(CenterHologramFormationLoader(this))
        registerFormationLoader(CustomHologramFormationLoader(this))
    }

    override fun registerFormationLoader(formation: HologramFormationLoader) {
        formationLoaders.add(formation)
    }

    override fun getFormationLoader(string: String) = formationLoaders.firstOrNull { it.formationName == string }

    override fun createHologram(location: Location): Hologram {
        val h = handler ?: throw IllegalStateException("There is no hologram, handler defined")
        val holo = h.create(location)
        return holo
    }

    override fun registerUpdaterLoader(loader: HologramUpdaterLoader) {
        this.updaterLoaders.add(loader)
    }

    override fun createDefaultUpdater(mine: HologramMine) = SyncHologramUpdater(plugin, mine)

    override fun loadUpdater(mine: HologramMine,
                             serializedUpdater: Map<String, Any>,
                             updaterName: String): HologramUpdater {
        return getUpdaterLoader(updaterName)?.loadUpdater(mine,
                serializedUpdater) ?: throw IllegalArgumentException("Unknown updater type $updaterName")

    }

    private fun getUpdaterLoader(updaterName: String): HologramUpdaterLoader? {
        return this.updaterLoaders.firstOrNull {
            it.updaterName == updaterName
        }
    }


}

