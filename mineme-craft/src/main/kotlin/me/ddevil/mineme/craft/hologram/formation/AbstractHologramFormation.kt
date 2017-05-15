package me.ddevil.mineme.craft.hologram.formation

import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.api.hologram.HologramFormation
import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.shiroi.util.misc.internal.AbstractDescribable

abstract class AbstractHologramFormation : AbstractDescribable, HologramFormation {
    val hologramManager: HologramManager?
    final override var alias: String
    final override var name: String

    @JvmOverloads
    constructor(hologramManager: HologramManager?, name: String, alias: String, description: List<String> = emptyList()) : super(description) {
        this.hologramManager = hologramManager
        this.alias = alias
        this.name = name
    }

    override val fullName: String
        get() = "$name($alias)"
    final override fun createHolograms(mine: Mine): Set<Hologram> {
        if(hologramManager==null){
            return emptySet()
        }
        return createHolograms0(mine)
    }

    abstract fun createHolograms0(mine: Mine): Set<Hologram>
}