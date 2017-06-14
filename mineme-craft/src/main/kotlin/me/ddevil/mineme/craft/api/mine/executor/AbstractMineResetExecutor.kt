package me.ddevil.mineme.craft.api.mine.executor

import com.google.common.collect.ImmutableMap
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import org.bukkit.block.Block

abstract class AbstractMineResetExecutor(override val mine: Mine) : MineResetExecutor {
    protected fun setRandomBlock(block: Block, repopulator: MineRepopulator) {
        val mat = repopulator.getBlock(mine, block.x, block.y, block.z)
        block.type = mat.first
        block.data = mat.second
    }

    final override fun serialize(): Map<String, Any> = ImmutableMap.builder<String, Any>()
            .put(MineMeConstants.MINE_EXECUTOR_TYPE_IDENTIFIER, type.name)
            .put(MineMeConstants.MINE_EXECUTOR_META_IDENTIFIER, serializeMeta())
            .build()

    abstract fun serializeMeta(): Map<String, Any>
}