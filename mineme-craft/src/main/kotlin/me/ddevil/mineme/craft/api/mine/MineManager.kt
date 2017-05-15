package me.ddevil.mineme.craft.api.mine

import com.sk89q.worldedit.regions.Region
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutor
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.shiroi.util.misc.Reloadable
import me.ddevil.shiroi.util.misc.Toggleable
import org.bukkit.inventory.ItemStack

interface MineManager : Toggleable, Reloadable {

    val defaultRepopulator: MineRepopulator

    val defaultMineIcon: ItemStack

    val mines: Set<Mine>

    val compositions: Set<MineComposition>

    val loaders: Set<MineLoader<*>>

    operator fun get(name: String): Mine?

    fun getRepopulator(name: String): MineRepopulator?

    fun getComposition(name: String): MineComposition?

    fun delete(mine: Mine)

    fun save(mine: Mine)

    fun save(mineComposition: MineComposition)

    fun <R : Region> getLoader(type: Class<R>): MineLoader<R>?
    fun getLoader(type: MineType): MineLoader<*>?

    fun registerMine(mine: Mine)

    fun registerMineComposition(composition: MineComposition)
    fun createDefaultResetExecutor(arg: Mine): MineResetExecutor
}

