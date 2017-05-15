package me.ddevil.mineme.craft.api.mine

import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutor
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.shiroi.util.misc.Nameable
import me.ddevil.shiroi.util.misc.Toggleable
import me.ddevil.shiroi.util.serialization.Serializable
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

interface Mine : Nameable, Iterable<Block>, Serializable, Toggleable {
    //<editor-fold desc="General" defaultstate="collapsed">
    var icon: ItemStack

    val type: MineType

    var composition: MineComposition

    var defaultRepopulator: MineRepopulator

    val world: World

    /**
     * Resets the mine
     */
    fun reset()

    /**
     * Resets the mine with the given [repopulator]

     * @param repopulator
     */
    fun reset(repopulator: MineRepopulator)

    /**
     * Resets the mine with the given [repopulator] and [executor]

     * @param repopulator
     */
    fun reset(repopulator: MineRepopulator, executor: MineResetExecutor)

    /**
     * Resets the mine with the given [executor]
     * @param executor
     */
    fun reset(executor: MineResetExecutor)

    /**
     * @param item The item to fill with (Must be placeable!).
     */
    fun fill(item: ItemStack)

    /**
     * Will remove all the blocks (not material) inside this mine, if
     * you wish to remove the material, use [.clearMaterials] instead!
     */
    fun clear()

    var resetDelay: Int

    var counting: Boolean

    var currentCountdown: Int

    val timeToNextReset: Int

    var defaultExecutor: MineResetExecutor

    var enabled: Boolean

    fun delete()

    val center: Location
    val topCenter: Location
    //</editor-fold>

}

