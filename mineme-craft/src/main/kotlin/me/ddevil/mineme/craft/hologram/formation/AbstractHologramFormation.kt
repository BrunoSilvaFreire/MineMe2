package me.ddevil.mineme.craft.hologram.formation

import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormation
import me.ddevil.util.immutableMap
import me.ddevil.util.misc.AbstractNameableDescribable
import me.ddevil.util.set

abstract class AbstractHologramFormation
@JvmOverloads
constructor(
        val hologramManager: HologramManager,
        name: String,
        alias: String,
        description: List<String> = emptyList()
) : AbstractNameableDescribable(name, alias, description), HologramFormation{
    override fun serialize(): Map<String, Any> = immutableMap {
        this[MineMeConstants.MINE_CONFIG_OPTION_NAME_IDENTIFIER] = name
        this[MineMeConstants.MINE_CONFIG_OPTION_META_IDENTIFIER] = meta
    }
}