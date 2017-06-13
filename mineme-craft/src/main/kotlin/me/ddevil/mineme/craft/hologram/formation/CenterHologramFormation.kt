package me.ddevil.mineme.craft.hologram.formation

import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.util.getIntOrNull
import me.ddevil.util.immutableMap
import me.ddevil.util.set

class CenterHologramFormation : AbstractHologramFormation {
    override val meta: Map<String, Any>
        get() =  immutableMap {
            this[Y_OFFSET_KEY] = yOffset
        }


    private var yOffset: Int

    constructor(hologramManager: HologramManager, yOffset: Int = 0) : super(
            hologramManager,
            NAME,
            ALIAS,
            DESCRIPTION
    ) {
        this.yOffset = yOffset
    }

    constructor(hologramManager: HologramManager, map: Map<String, Any>) : super(
            hologramManager,
            NAME,
            ALIAS,
            DESCRIPTION) {
        this.yOffset = map.getIntOrNull(Y_OFFSET_KEY) ?: 0

    }

    companion object {
        const val NAME = "center"
        const val ALIAS = "Center"
        val DESCRIPTION = listOf("Creates a single hologram on the top of the validMine.")
        const val Y_OFFSET_KEY = "yOffset"
    }

    override fun canApplyTo(mine: HologramMine) = true

    override fun createHolograms(mine: HologramMine): Set<Hologram> {
        val center = mine.topCenter
        center.y += mine.hologramText.size * MineMeConstants.HOLOGRAM_LINE_SIZE + yOffset
        return setOf(hologramManager.createHologram(center))
    }

}