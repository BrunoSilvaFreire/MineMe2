package me.ddevil.mineme.craft.api.mine

import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutor
import me.ddevil.shiroi.util.misc.Toggleable
import me.ddevil.util.Serializable
import me.ddevil.util.misc.Nameable
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

interface Mine : Nameable, Iterable<Block>, Serializable, Toggleable, Listener {
    //<editor-fold desc="General" defaultstate="collapsed">
    var icon: ItemStack

    val type: MineType

    var composition: MineComposition

    var defaultRepopulator: MineRepopulator

    val world: World

    /**
     * Resets the validMine
     */
    fun reset()

    /**
     * Resets the validMine with the given [repopulator]

     * @param repopulator
     */
    fun reset(repopulator: MineRepopulator)

    /**
     * Resets the validMine with the given [repopulator] and [executor]

     * @param repopulator
     */
    fun reset(repopulator: MineRepopulator, executor: MineResetExecutor)

    /**
     * Resets the validMine with the given [executor]
     * @param executor
     */
    fun reset(executor: MineResetExecutor)

    /**
     * @param item The item to fill with (Must be placeable!).
     */
    fun fill(item: ItemStack)

    /**
     * Will remove all the blocks (not material) inside this validMine, if
     * you wish to remove the material, use [.clearMaterials] instead!
     */
    fun clear()

    val minedBlocks: Int

    val minedBlocksPercentage: Double

    val volume: Int

    var resetDelay: Int

    var counting: Boolean

    var currentCountdown: Int

    val timeToNextReset: Int

    var defaultExecutor: MineResetExecutor

    val enabled: Boolean

    fun delete()

    val center: Location
    val topCenter: Location

    val clockListeners: List<MineClockListener>

    fun addClockListener(function: () -> Unit): MineClockListener

    fun addClockListener(listener: MineClockListener)

    fun removeClockListener(listener: MineClockListener)


    //</editor-fold>

    fun save()
}

