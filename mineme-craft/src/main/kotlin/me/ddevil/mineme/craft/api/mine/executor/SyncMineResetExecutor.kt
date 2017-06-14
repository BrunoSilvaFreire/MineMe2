package me.ddevil.mineme.craft.api.mine.executor

import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import org.bukkit.Location
import org.bukkit.entity.Player

class SyncMineResetExecutor(mine: Mine) : AbstractMineResetExecutor(mine) {


    override fun serializeMeta(): Map<String, Any> = emptyMap()

    override val type get() = MineResetExecutorType.SYNC

    override fun reset(repopulator: MineRepopulator) {
        for (block in mine) {
            setRandomBlock(block, repopulator)
        }
    }

    override fun getResetTeleportLocation(player: Player): Location {
        val loc = player.location
        loc.y = mine.maxY.toDouble()
        return loc
    }

}