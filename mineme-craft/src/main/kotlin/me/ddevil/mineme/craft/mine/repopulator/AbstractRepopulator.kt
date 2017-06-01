package me.ddevil.mineme.craft.mine.repopulator

import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.util.misc.AbstractNameableDescribable

abstract class AbstractRepopulator(
        name: String,
        alias: String,
        description: List<String>
) : AbstractNameableDescribable(name, alias, description), MineRepopulator
