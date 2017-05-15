package me.ddevil.mineme.craft.api.mine.executor

import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.shiroi.util.serialization.Serializable

interface MineResetExecutor : Serializable {
    val type: MineResetExecutorType
    val mine: Mine
    fun reset(repopulator: MineRepopulator)
}