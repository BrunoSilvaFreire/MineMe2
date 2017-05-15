package me.ddevil.mineme.craft.mine.repopulator

import me.ddevil.mineme.craft.api.mine.Mine
import org.bukkit.inventory.ItemStack

class FillMineRepopulator constructor(val itemStack: ItemStack) : AbstractRepopulator(
        "fill",
        "Fill",
        listOf(
                "This repopulator fills the hole",
                "mine with a single item."
        )
) {
    override fun getBlock(mine: Mine, x: Int, y: Int, z: Int) = Pair(itemStack.type, itemStack.durability.toByte())


}