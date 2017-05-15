package me.ddevil.mineme.craft.mine.misc

import com.sk89q.worldedit.BlockVector
import com.sk89q.worldedit.regions.Region
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.block.Block

class WorldEditIterator(val world: World, region: Region) : Iterator<Block> {
    private var regionIterator: MutableIterator<BlockVector>


    init {
        this.regionIterator = region.iterator()
    }

    override fun hasNext() = regionIterator.hasNext()

    override fun next(): Block {
        val next = regionIterator.next()
        return world.getBlockAt(next.blockX, next.blockY, next.blockZ)
    }

}