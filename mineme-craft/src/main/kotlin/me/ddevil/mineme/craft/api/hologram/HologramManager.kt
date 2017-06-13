package me.ddevil.mineme.craft.api.hologram

import me.ddevil.mineme.craft.api.hologram.formation.HologramFormation
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormationLoader
import me.ddevil.mineme.craft.api.hologram.updater.HologramUpdater
import me.ddevil.mineme.craft.api.hologram.updater.HologramUpdaterLoader
import me.ddevil.mineme.craft.api.mine.HologramMine
import org.bukkit.Location

interface HologramManager {

    fun createHologram(location: Location): Hologram

    fun createDefaultFormation(mine: HologramMine): HologramFormation

    fun getFormationLoader(string: String): HologramFormationLoader?

    fun registerFormationLoader(formation: HologramFormationLoader)

    fun createDefaultUpdater(mine: HologramMine): HologramUpdater

    fun loadUpdater(mine: HologramMine,serializedUpdater: Map<String, Any>, updaterName: String): HologramUpdater

    fun registerUpdaterLoader(loader: HologramUpdaterLoader)

}