package me.ddevil.mineme.craft.mine.repopulator

import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.shiroi.util.misc.internal.AbstractDescribable

abstract class AbstractRepopulator
@JvmOverloads
constructor(
        override var name: String,
        override var alias: String,
        description: List<String> = emptyList()) : AbstractDescribable(description), MineRepopulator {

    override val fullName: String
        get() = "$name($alias)"
}