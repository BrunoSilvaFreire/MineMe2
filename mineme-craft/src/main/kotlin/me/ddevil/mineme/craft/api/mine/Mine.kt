package me.ddevil.mineme.craft.api.mine

import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutor
import me.ddevil.shiroi.util.misc.Toggleable
import me.ddevil.util.Serializable
import me.ddevil.util.misc.Nameable
import me.ddevil.util.vector.Vector3
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import java.io.File

interface Mine : Nameable, Iterable<Block>, Serializable, Toggleable, Listener {
    //<editor-fold desc="General" defaultstate="collapsed">
    var icon: ItemStack

    val type: MineType

    var composition: MineComposition

    var defaultRepopulator: MineRepopulator

    val world: World

    val file: File

    fun unload()

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

    val players: List<Player>

    val minedBlocks: Int

    val minedBlocksPercentage: Double

    val maxY: Int

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

    operator fun contains(block: Block): Boolean

    operator fun contains(player: Player): Boolean

    operator fun contains(location: Location): Boolean

    operator fun contains(vector: Vector): Boolean

    operator fun contains(vector: Vector3<*>): Boolean

    //</editor-fold>

    fun save()
}

