package me.ddevil.mineme.craft.hologram.updater.loader

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.hologram.updater.HologramUpdaterFactory
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.mineme.craft.hologram.updater.SyncHologramUpdater

class SyncHologramUpdaterFactory(val plugin: MineMe) : HologramUpdaterFactory {
    override val updaterName = "sync"

    override fun loadUpdater(mine: HologramMine, map: Map<String, Any>) = SyncHologramUpdater(plugin, mine)

    override fun createUpdater(mine: HologramMine) = SyncHologramUpdater(plugin, mine)

}