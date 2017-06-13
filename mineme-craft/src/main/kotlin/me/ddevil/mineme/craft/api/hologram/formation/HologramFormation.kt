package me.ddevil.mineme.craft.api.hologram.formation

import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.util.Serializable
import me.ddevil.util.misc.Describable
import me.ddevil.util.misc.Nameable

interface HologramFormation : Nameable, Describable, Serializable {
    val meta: Map<String, Any>
    fun canApplyTo(mine: HologramMine): Boolean

    fun createHolograms(mine: HologramMine): Set<Hologram>
}