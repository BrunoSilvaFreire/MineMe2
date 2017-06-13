package me.ddevil.mineme.craft.hologram.formation

import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.mine.HologramMine
import me.ddevil.shiroi.craft.util.toLocation
import me.ddevil.util.getList
import me.ddevil.util.immutableMap
import me.ddevil.util.set
import me.ddevil.util.vector.DoubleVector3
import me.ddevil.util.vector.Vector3

class CustomHologramFormation : AbstractHologramFormation {

    val positions: List<Vector3<Double>>

    constructor(
            hologramManager: HologramManager,
            positions: List<Vector3<Double>>
    ) : super(hologramManager, NAME, ALIAS, DESCRIPTION) {
        this.positions = positions
    }

    constructor(
            hologramManager: HologramManager,
            map: Map<String, Any>
    ) : super(hologramManager, NAME, ALIAS, DESCRIPTION) {
        this.positions = map.getList<Map<String, Any>>(POSITIONS_KEY).map { DoubleVector3(it) }
    }

    override val meta: Map<String, Any>
        get() = immutableMap {
            this[POSITIONS_KEY] = positions.map { it.serialize() }
        }


    companion object {
        const val NAME = "custom"
        const val ALIAS = "Custom"
        const val POSITIONS_KEY = "positions"
        val DESCRIPTION = listOf(
                "Lets the user define exactly",
                "where to place the holograms"
        )
    }

    override fun canApplyTo(mine: HologramMine) = true

    override fun createHolograms(mine: HologramMine): Set<Hologram> {
        val set = HashSet<Hologram>()
        for (position in positions) {
            set += hologramManager.createHologram(position.toLocation(mine.world))
        }
        return set
    }
}