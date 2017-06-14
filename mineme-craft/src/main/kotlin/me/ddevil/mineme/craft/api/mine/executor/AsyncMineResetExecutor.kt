package me.ddevil.mineme.craft.api.mine.executor

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.MineMeCraftConstants
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.shiroi.craft.util.toVector3
import me.ddevil.util.immutableMap
import me.ddevil.util.set
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class AsyncMineResetExecutor(
        mine: Mine,
        var teleportLocation: Location,
        var blocksPerSeconds: Int, private var plugin: MineMe
) : AbstractMineResetExecutor(
        mine) {


    override fun getResetTeleportLocation(player: Player): Location {
        return teleportLocation
    }


    override fun serializeMeta(): Map<String, Any> = immutableMap {
        this[MineMeCraftConstants.MINE_RESET_EXECUTOR_BLOCKS_PER_SECOND_KEY] = blocksPerSeconds
        this[MineMeCraftConstants.MINE_RESET_EXECUTOR_TELEPORT_LOCATION_KEY] = teleportLocation.toVector3().serialize()
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