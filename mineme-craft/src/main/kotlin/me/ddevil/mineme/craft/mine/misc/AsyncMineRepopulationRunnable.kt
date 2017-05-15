package me.ddevil.mineme.craft.mine.misc

import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import org.bukkit.block.Block
import org.bukkit.scheduler.BukkitRunnable

class AsyncMineRepopulationRunnable(val mine: Mine, val repopulator: MineRepopulator) : BukkitRunnable() {
    private var iterator: Iterator<Block>

    init {
        this.iterator = mine.iterator()
    }

    override fun run() {
        if (iterator.hasNext()) {
            val block = iterator.next()
            val pair = repopulator.getBlock(mine, block.x, block.y, block.z)
            block.type = pair.first
            block.data = pair.second
        } else {
            cancel()
        }
    }

}