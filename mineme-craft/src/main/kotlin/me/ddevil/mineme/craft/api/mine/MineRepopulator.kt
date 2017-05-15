package me.ddevil.mineme.craft.api.mine

import me.ddevil.shiroi.util.misc.Describable
import me.ddevil.shiroi.util.misc.Nameable
import org.bukkit.Material

interface MineRepopulator : Nameable, Describable {

    fun getBlock(mine: Mine, x: Int, y: Int, z: Int): Pair<Material, Byte>


}