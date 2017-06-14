package me.ddevil.mineme.craft.util

import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.shiroi.craft.misc.variable.MessageVariable
import me.ddevil.util.toSecondsString

fun Mine.exportMessageVariables() = arrayOf(
        MessageVariable(MineMeConstants.MINE_NAME_IDENTIFIER, name),
        MessageVariable(MineMeConstants.MINE_ALIAS_IDENTIFIER, alias),
        MessageVariable(MineMeConstants.MINE_TYPE_IDENTIFIER, type.toString()),
        MessageVariable(MineMeConstants.MINE_ENABLED_IDENTIFIER, enabled.toString()),
        MessageVariable(MineMeConstants.MINE_COMPOSITION_IDENTIFIER, composition.name),
        MessageVariable(MineMeConstants.MINE_WORLD_IDENTIFIER, world.name),
        MessageVariable(MineMeConstants.MINE_MINED_BLOCKS_IDENTIFIER, minedBlocks.toString()),
        MessageVariable(MineMeConstants.MINE_VOLUME_IDENTIFIER, volume.toString()),
        MessageVariable(MineMeConstants.MINE_BLOCKS_PERCENT_LEFT_IDENTIFIER,
                MineMeConstants.DECIMAL_FORMAT.format(minedBlocksPercentage)),
        MessageVariable(MineMeConstants.MINE_RESET_TIME_PASSED_IDENTIFIER, currentCountdown.toSecondsString()),
        MessageVariable(MineMeConstants.MINE_TOTAL_RESET_TIME_IDENTIFIER, resetDelay.toSecondsString()),
        MessageVariable(MineMeConstants.MINE_RESET_TIME_LEFT_IDENTIFIER, timeToNextReset.toSecondsString())
)

fun MineComposition.exportMessageVariables() = arrayOf(
        MessageVariable(MineMeConstants.MINE_NAME_IDENTIFIER, name),
        MessageVariable(MineMeConstants.MINE_ALIAS_IDENTIFIER, alias)
)