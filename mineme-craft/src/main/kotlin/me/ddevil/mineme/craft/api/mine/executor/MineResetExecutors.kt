package me.ddevil.mineme.craft.api.mine.executor

import com.google.common.collect.ImmutableMap
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.MineMeCraftConstants
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.util.immutableMap
import me.ddevil.util.set
import org.bukkit.block.Block
import org.bukkit.scheduler.BukkitRunnable

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

class AsyncMineResetExecutor(mine: Mine,
                             private var blocksPerSeconds: Int,
                             private var plugin: MineMe) : AbstractMineResetExecutor(
        mine) {

    override fun serializeMeta(): Map<String, Any> = immutableMap {
        this[MineMeCraftConstants.MINE_RESET_EXECUTOR_BLOCKS_PER_SECOND_KEY] = blocksPerSeconds
    }

    override val type get() = MineResetExecutorType.ASYNC

    private val blocksPerRun: Long get() {
        return if (blocksPerSeconds > 20) {
            (blocksPerSeconds / 20).toLong()
        } else {
            1
        }
    }

    private val timerDelay: Long get() {
        return if (blocksPerSeconds >= 20) {
            1
        } else {
            (20 / blocksPerSeconds).toLong()
        }
    }

    override fun reset(repopulator: MineRepopulator) {
        object : BukkitRunnable() {
            val iterator = mine.iterator()
            override fun run() {
                for (i in 0..blocksPerRun - 1) {
                    if (!iterator.hasNext()) {
                        cancel()
                        return
                    }
                    setRandomBlock(iterator.next(), repopulator)
                }
            }
        }.runTaskTimer(plugin, timerDelay, timerDelay)
    }


}

class SyncMineResetExecutor(mine: Mine) : AbstractMineResetExecutor(mine) {
    override fun serializeMeta(): Map<String, Any> = emptyMap()

    override val type get() = MineResetExecutorType.SYNC

    override fun reset(repopulator: MineRepopulator) {
        for (block in mine) {
            setRandomBlock(block, repopulator)
        }
    }


}