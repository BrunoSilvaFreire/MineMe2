package me.ddevil.mineme.craft.api.mine.executor

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.MineMeCraftConstants
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.util.getOrException

enum class MineResetExecutorType {
    SYNC {
        override fun create(mine: Mine, map: Map<String, Any>, plugin: MineMe): MineResetExecutor {
            return SyncMineResetExecutor(mine)
        }
    },
    ASYNC {
        override fun create(mine: Mine, map: Map<String, Any>, plugin: MineMe): MineResetExecutor {
            val time = map.getOrException<Int>(MineMeCraftConstants.MINE_RESET_EXECUTOR_BLOCKS_PER_SECOND_KEY)
            return AsyncMineResetExecutor(mine, time, plugin)
        }
    };

    abstract fun create(mine: Mine, map: Map<String, Any>, plugin: MineMe): MineResetExecutor

    companion object {
        fun getType(name: String): MineResetExecutorType {
            return valueOf(name.toUpperCase())
        }
    }
}