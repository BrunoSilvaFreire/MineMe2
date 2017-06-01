package me.ddevil.mineme.craft.api.mine

import me.ddevil.util.misc.Describable
import me.ddevil.util.misc.Nameable
import org.bukkit.Material

/**
 * Selects which blocks will be used for a
 */
interface MineRepopulator : Nameable, Describable {

    fun getBlock(mine: Mine, x: Int, y: Int, z: Int): Pair<Material, Byte>


}