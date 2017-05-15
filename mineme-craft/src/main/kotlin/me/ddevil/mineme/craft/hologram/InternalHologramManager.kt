package me.ddevil.mineme.craft.hologram

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.MineMeCraftConstants
import me.ddevil.mineme.craft.api.hologram.HologramFormation
import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.hologram.HologramProvider
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.hologram.provider.holographicdisplays.HolographicDisplaysHandler
import me.ddevil.shiroi.craft.log.DebugLevel
import org.bukkit.Bukkit
import org.bukkit.Location
import java.util.*

class InternalHologramManager
constructor(val plugin: MineMe) : HologramManager {
    private val formations: MutableSet<HologramFormation>
    private var handler: HologramHandler?

    init {
        formations = HashSet()
        handler = null
        if (plugin.configManager.getValue(MineMeConfigValue.HOLOGRAM_ENABLED))
            try {
                val value = plugin.configManager.getValue(MineMeConfigValue.HOLOGRAM_PROVIDER)
                val provider = HologramProvider.valueOf(value)
                if (provider == HologramProvider.HOLOGRAPHIC_DISPLAYS) {
                    if (Bukkit.getPluginManager().isPluginEnabled(MineMeCraftConstants.HOLOGRAPHIC_DISPLAYS_NAME)) {
                        handler = HolographicDisplaysHandler(plugin)
                    }
                }

            } catch(e: Exception) {
                throw IllegalStateException("There was an error while enabling ")
            }
        if (handler != null) {
            plugin.pluginLogger.log("", DebugLevel.NO_BIG_DEAL)
        }
    }

    override fun registerFormation(formation: HologramFormation) {
        formations.add(formation)
    }

    override fun getFormation(string: String) = formations.firstOrNull { it.name == string }

    override fun createHologram(location: Location) = handler?.create(location)

}

