package me.ddevil.mineme.craft.hologram.formation.loader

import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormation
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.mineme.craft.hologram.formation.CenterHologramFormation

class CenterHologramFormationFactory(hologramManager: HologramManager) : AbstractHologramFormationFactory(hologramManager) {
    override val formationName get() = CenterHologramFormation.NAME

    override fun loadFormation(mine: HologramMine, map: Map<String, Any>): HologramFormation {
        return CenterHologramFormation(hologramManager, map)
    }

    override fun createFormation(mine: HologramMine): HologramFormation {
        return CenterHologramFormation(hologramManager)
    }
}

