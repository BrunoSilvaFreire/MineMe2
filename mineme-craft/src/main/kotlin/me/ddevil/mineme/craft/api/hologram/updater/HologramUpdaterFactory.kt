package me.ddevil.mineme.craft.api.hologram.updater

import me.ddevil.mineme.craft.api.mine.HologramMine

interface HologramUpdaterFactory {

    val updaterName: String

    fun loadUpdater(mine: HologramMine, map: Map<String, Any>): HologramUpdater

    fun createUpdater(mine: HologramMine): HologramUpdater
}