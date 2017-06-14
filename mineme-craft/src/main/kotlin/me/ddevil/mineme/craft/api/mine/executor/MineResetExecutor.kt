package me.ddevil.mineme.craft.api.mine.executor

import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.util.Serializable
import org.bukkit.Location
import org.bukkit.entity.Player

interface MineResetExecutor : Serializable {
    val type: MineResetExecutorType
    val mine: Mine
    fun getResetTeleportLocation(player: Player): Location
    fun reset(repopulator: MineRepopulator)

}