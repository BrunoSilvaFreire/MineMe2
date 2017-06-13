package me.ddevil.mineme.craft.mine

import com.sk89q.worldedit.regions.Polygonal2DRegion
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormation
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.mineme.craft.api.mine.MineType
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.util.toShiroi
import me.ddevil.mineme.craft.util.toWE
import me.ddevil.util.getInt
import me.ddevil.util.getList
import me.ddevil.util.immutableMap
import me.ddevil.util.set
import me.ddevil.util.vector.IntVector2
import org.bukkit.inventory.ItemStack

class PolygonalMine : AbstractHologramMine<Polygonal2DRegion> {
    companion object {
        const val POINTS_KEY = "points"
    }

    override val type = MineType.POLYGON

    override fun loadRegion(map: Map<String, Any>): Polygonal2DRegion {
        val points = map.getList<Map<String, Any>>(POINTS_KEY).map {
            IntVector2(it).toWE()
        }
        val minY = map.getInt(MineMeConstants.MINIMUM_Y_KEY)
        val maxY = map.getInt(MineMeConstants.MAXIMUM_Y_KEY)
        return Polygonal2DRegion(null, points, minY, maxY)
    }

    override fun serializeRegion(): Map<String, Any> = immutableMap {
        this[POINTS_KEY] = region.points.map { it.toShiroi() }
        this[MineMeConstants.MINIMUM_Y_KEY] = region.minimumY
        this[MineMeConstants.MAXIMUM_Y_KEY] = region.maximumY
    }

    constructor(plugin: MineMe,
                alias: String,
                name: String,
                composition: MineComposition,
                region: Polygonal2DRegion,
                icon: ItemStack = plugin.mineManager.defaultMineIcon,
                defaultRepopulator: MineRepopulator = plugin.mineManager.defaultRepopulator,
                resetDelay: Int = plugin.configManager.getValue(MineMeConfigValue.DEFAULT_MINE_RESET_DELAY),
                formation: HologramFormation? = null,
                useCustomHologramText: Boolean = false,
                customHologramText: List<String> = emptyList()
    ) : super(plugin, alias, name, composition, region, icon, defaultRepopulator, resetDelay, formation,
            useCustomHologramText, customHologramText)

    constructor(plugin: MineMe, map: Map<String, Any>) : super(plugin, map)

}