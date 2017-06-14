package me.ddevil.mineme.craft.event

import me.ddevil.mineme.craft.api.mine.Mine
import org.bukkit.event.Cancellable

open class CancellableMineEvent(mine: Mine) : MineEvent(mine), Cancellable {
    private var cancelled = false

    override fun setCancelled(cancel: Boolean) {
        this.cancelled = cancel
    }

    override fun isCancelled() = cancelled

}