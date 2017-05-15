package me.ddevil.mineme.craft.api.mine.executor

import com.google.common.collect.ImmutableMap
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineRepopulator
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

class AsyncMineResetExecutor(mine: Mine, blocksPerSeconds: Int, private var plugin: MineMe) : AbstractMineResetExecutor(mine) {
    companion object {
        val BLOCKS_PER_SECOND_IDENTIFIER = "blocksPerSecond"
    }

    override fun serializeMeta(): Map<String, Any> = ImmutableMap.builder<String, Any>()
            .put(BLOCKS_PER_SECOND_IDENTIFIER, blocksPerSeconds)
            .build()

    private var timerDelay: Long
    private var blocksPerRun: Long
    private var _blocksPerSeconds: Int
    private var blocksPerSeconds: Int
        set(value) {
            _blocksPerSeconds = value
            if (blocksPerSeconds <= 20) {
                timerDelay = 20 / blocksPerSeconds.toLong()
                blocksPerRun = 1
            } else {
                timerDelay = 0
                blocksPerRun = blocksPerSeconds.toLong() / 20
            }
        }
        get() = _blocksPerSeconds
    override val type get() = MineResetExecutorType.ASYNC

    init {
        this._blocksPerSeconds = blocksPerSeconds
        if (blocksPerSeconds <= 20) {
            timerDelay = 20 / blocksPerSeconds.toLong()
            blocksPerRun = 1
        } else {
            timerDelay = 0
            blocksPerRun = blocksPerSeconds.toLong() / 20
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