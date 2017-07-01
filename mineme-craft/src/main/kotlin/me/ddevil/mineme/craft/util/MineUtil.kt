package me.ddevil.mineme.craft.util

import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.shiroi.craft.misc.variable.MessageVariable

fun MineComposition.exportMessageVariables() = arrayOf(
        MessageVariable(MineMeConstants.MINE_NAME_IDENTIFIER, name),
        MessageVariable(MineMeConstants.MINE_ALIAS_IDENTIFIER, alias)
)