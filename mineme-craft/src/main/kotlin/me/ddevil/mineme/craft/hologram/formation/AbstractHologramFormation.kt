package me.ddevil.mineme.craft.hologram.formation

import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.api.hologram.HologramFormation
import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.util.misc.AbstractNameableDescribable

abstract class AbstractHologramFormation : AbstractNameableDescribable, HologramFormation {
    val hologramManager: HologramManager?


    @JvmOverloads
    constructor(hologramManager: HologramManager?,
                name: String,
                alias: String,
                description: List<String> = emptyList()) : super(name, alias, description) {
        this.hologramManager = hologramManager
    }

    final override fun createHolograms(mine: Mine): Set<Hologram> {
        if (hologramManager == null) {
            return emptySet()
        }
        return createHolograms0(mine)
    }

    abstract fun createHolograms0(mine: Mine): Set<Hologram>
}