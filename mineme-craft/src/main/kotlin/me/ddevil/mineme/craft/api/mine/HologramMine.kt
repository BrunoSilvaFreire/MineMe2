package me.ddevil.mineme.craft.api.mine

import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormation
import me.ddevil.mineme.craft.api.hologram.updater.HologramUpdater

interface HologramMine : Mine {

    val formation: HologramFormation?

    var hologramUpdater: HologramUpdater

    var hologramsEnabled: Boolean

    val holograms: Set<Hologram>

    fun addHologram(hologram: Hologram)

    fun removeHologram(hologram: Hologram)

    fun updateHolograms()

    val hologramText: List<String>

    var useCustomHologramText: Boolean

    var customHologramText: List<String>
}
