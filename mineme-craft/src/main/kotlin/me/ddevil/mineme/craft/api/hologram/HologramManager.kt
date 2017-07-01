package me.ddevil.mineme.craft.api.hologram

import me.ddevil.mineme.craft.api.hologram.formation.HologramFormation
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormationFactory
import me.ddevil.mineme.craft.api.hologram.updater.HologramUpdater
import me.ddevil.mineme.craft.api.hologram.updater.HologramUpdaterFactory
import me.ddevil.mineme.craft.api.mine.HologramMine
import org.bukkit.Location

interface HologramManager {

    val hasHandler: Boolean

    fun createHologram(location: Location): Hologram

    fun createDefaultFormation(mine: HologramMine): HologramFormation

    fun getFormationLoader(string: String): HologramFormationFactory?

    fun registerFormationLoader(formation: HologramFormationFactory)

    fun createDefaultUpdater(mine: HologramMine): HologramUpdater

    fun loadUpdater(mine: HologramMine, serializedUpdater: Map<String, Any>, updaterName: String): HologramUpdater

    fun registerUpdaterLoader(factory: HologramUpdaterFactory)

}