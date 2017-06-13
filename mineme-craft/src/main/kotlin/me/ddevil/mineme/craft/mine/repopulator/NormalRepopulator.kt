package me.ddevil.mineme.craft.mine.repopulator

import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.shiroi.craft.util.toBukkit
import org.bukkit.Material

class NormalRepopulator
private constructor() : AbstractRepopulator(
        "normal",
        "Normal",
        listOf(
                "The normal repopulator, it",
                "repopulates the validMine based on",
                "it's composition."
        )) {

    override fun getBlock(mine: Mine, x: Int, y: Int, z: Int): Pair<Material, Byte> {
        val composition = mine.composition.randomMaterial()
        return Pair(composition.material.toBukkit(), composition.data)
    }

    companion object {
        val INSTANCE = NormalRepopulator()
    }

}