package me.ddevil.mineme.craft.api.mine

import com.sk89q.worldedit.regions.Region
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutor
import me.ddevil.mineme.craft.mine.config.MineConfig
import me.ddevil.mineme.craft.mine.config.ValidMineConfig
import me.ddevil.shiroi.craft.misc.CraftReloadable
import me.ddevil.shiroi.util.misc.Toggleable
import org.bukkit.inventory.ItemStack

interface MineManager : Toggleable, CraftReloadable {

    val defaultRepopulator: MineRepopulator

    val defaultMineIcon: ItemStack

    val mines: Set<Mine>

    val compositions: Set<MineComposition>

    val loaders: Set<MineLoader<*>>

    fun getMine(name: String): Mine?

    fun getRepopulator(name: String): MineRepopulator?

    fun getComposition(name: String): MineComposition?

    fun delete(mine: Mine)

    fun save(mine: Mine)

    fun save(mineComposition: MineComposition)

    fun <R : Region> getLoader(type: Class<R>): MineLoader<R>?

    fun getLoader(type: MineType): MineLoader<*>

    fun createDefaultResetExecutor(arg: Mine): MineResetExecutor

    fun hasMine(name: String): Boolean

    fun hasComposition(name: String): Boolean

    fun disable(mine: Mine)

    fun findUnloadedMines(): List<MineConfig>

    fun loadMine(validMineConfig: ValidMineConfig): Mine
    fun registerMine(mine: Mine)
    fun registerMineComposition(composition: MineComposition)
}

