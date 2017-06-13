package me.ddevil.mineme.craft.hologram.formation.loader

import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormation
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.mineme.craft.hologram.formation.CustomHologramFormation
import me.ddevil.shiroi.craft.util.toVector3

class CustomHologramFormationLoader(hologramManager: HologramManager) : AbstractHologramFormationLoader(hologramManager) {
    override val formationName: String get() = CustomHologramFormation.NAME

    override fun loadFormation(mine: HologramMine, map: Map<String, Any>): HologramFormation {
        return CustomHologramFormation(hologramManager, map)
    }

    override fun createFormation(mine: HologramMine): HologramFormation {
        return CustomHologramFormation(hologramManager, listOf(mine.topCenter.toVector3()))
    }
}