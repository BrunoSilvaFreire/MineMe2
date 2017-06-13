package me.ddevil.mineme.craft.hologram.updater

import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.hologram.updater.HologramUpdater
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.util.immutableMap
import me.ddevil.util.set

abstract class AbstractHologramUpdater(val plugin: MineMe, val mine: HologramMine) : HologramUpdater {

    abstract val name: String
    abstract val meta: Map<String, Any>

    override fun serialize(): Map<String, Any> = immutableMap {
        this[MineMeConstants.MINE_CONFIG_OPTION_NAME_IDENTIFIER] = name
        this[MineMeConstants.MINE_CONFIG_OPTION_META_IDENTIFIER] = meta
    }
}