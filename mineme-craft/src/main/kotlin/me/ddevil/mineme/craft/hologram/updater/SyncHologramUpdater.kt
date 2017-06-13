package me.ddevil.mineme.craft.hologram.updater

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.mineme.craft.api.mine.MineClockListener

class SyncHologramUpdater(plugin: MineMe, mine: HologramMine) : AbstractHologramUpdater(plugin, mine) {
    lateinit private var listener: MineClockListener
    override val name = "sync"
    override val meta: Map<String, Any> = emptyMap()
    override fun disable() {
        mine.removeClockListener(listener)
    }

    override fun enable() {
        this.listener = mine.addClockListener {
            mine.updateHolograms()
        }
    }
}

