package me.ddevil.mineme.craft.api.mine

import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.api.hologram.HologramFormation

interface HologramMine : Mine {

    val formation: HologramFormation?

    var hologramsEnabled: Boolean

    val holograms: Set<Hologram>

    fun addHologram(hologram: Hologram)

    fun removeHologram(hologram: Hologram)

}
