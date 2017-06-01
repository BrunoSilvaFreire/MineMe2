package me.ddevil.mineme.craft.api.hologram

import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.util.misc.Describable
import me.ddevil.util.misc.Nameable

interface HologramFormation : Nameable, Describable {

    fun canApplyTo(mine: HologramMine): Boolean

    fun createHolograms(mine: Mine): Set<Hologram>

}