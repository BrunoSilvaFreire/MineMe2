package me.ddevil.mineme.craft.hologram.formation

import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.mineme.craft.api.mine.Mine

class EmptyHologramFormation(hologramManager: HologramManager?) : AbstractHologramFormation(
        hologramManager,
        "emptyFormation",
        "Empty Formation",
        listOf(
                "An empty formation, creates",
                "no holograms."
        )) {

    override fun canApplyTo(mine: HologramMine) = true
    override fun createHolograms0(mine: Mine): Set<Hologram> = emptySet()
}