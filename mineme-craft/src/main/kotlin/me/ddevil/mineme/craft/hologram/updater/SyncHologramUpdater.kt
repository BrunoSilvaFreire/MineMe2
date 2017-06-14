package me.ddevil.mineme.craft.hologram.updater

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.mineme.craft.api.mine.MineClockListener

class SyncHologramUpdater(plugin: MineMe, mine: HologramMine) : AbstractHologramUpdater(plugin, mine) {
    private var listener: MineClockListener? = null
    override val name = "sync"
    override val meta: Map<String, Any> = emptyMap()
    override fun disable() {
        val l = listener
        if (l != null) {
            mine.removeClockListener(l)
        }
    }

    override fun enable() {
        this.listener = mine.addClockListener {
            mine.updateHolograms()
        }
    }
}

