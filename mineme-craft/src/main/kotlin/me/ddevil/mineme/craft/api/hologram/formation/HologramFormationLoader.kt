package me.ddevil.mineme.craft.api.hologram.formation

import me.ddevil.mineme.craft.api.mine.HologramMine

interface HologramFormationLoader {

    val formationName: String

    fun loadFormation(mine: HologramMine, map: Map<String, Any>): HologramFormation

    fun createFormation(mine: HologramMine): HologramFormation
}