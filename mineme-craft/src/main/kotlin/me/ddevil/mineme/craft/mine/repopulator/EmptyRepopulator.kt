package me.ddevil.mineme.craft.mine.repopulator

import me.ddevil.mineme.craft.api.mine.Mine
import org.bukkit.Material

class EmptyRepopulator private constructor() : AbstractRepopulator(
        "empty",
        "Empty",
        listOf(
                "This repopulator basically empties",
                "every validMine, it always returns air, ",
                "no matter what."
        )
) {
    companion object {
        val INSTANCE = EmptyRepopulator()
    }

    private val pair = Pair<Material, Byte>(Material.AIR, 0)

    override fun getBlock(mine: Mine, x: Int, y: Int, z: Int) = pair

}