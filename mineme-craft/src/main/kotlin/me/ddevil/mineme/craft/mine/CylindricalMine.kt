package me.ddevil.mineme.craft.mine

import com.sk89q.worldedit.Vector
import com.sk89q.worldedit.regions.CylinderRegion
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormation
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.mineme.craft.api.mine.MineType
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.util.toWE
import me.ddevil.shiroi.craft.util.toVector
import me.ddevil.util.getInt
import me.ddevil.util.getMap
import me.ddevil.util.vector.DoubleVector2
import org.bukkit.inventory.ItemStack

class CylindricalMine : AbstractHologramMine<CylinderRegion> {


    override val type = MineType.CYLINDRICAL

    constructor(plugin: MineMe,
                alias: String,
                name: String,
                composition: MineComposition,
                region: CylinderRegion,
                icon: ItemStack = plugin.mineManager.defaultMineIcon,
                defaultRepopulator: MineRepopulator = plugin.mineManager.defaultRepopulator,
                resetDelay: Int = plugin.configManager.getValue(MineMeConfigValue.DEFAULT_MINE_RESET_DELAY),
                formation: HologramFormation? = null,
                useCustomHologramText: Boolean = false,
                customHologramText: List<String> = emptyList()
    ) : super(plugin, alias, name, composition, region, icon, defaultRepopulator, resetDelay, formation,
            useCustomHologramText, customHologramText)

    constructor(plugin: MineMe, map: Map<String, Any>) : super(plugin, map)

    override fun loadRegion(map: Map<String, Any>): CylinderRegion {
        val center = DoubleVector2(map.getMap(MineMeConstants.CENTER_KEY)).toVector(0)
        val radius = DoubleVector2(map.getMap(MineMeConstants.RADIUS_KEY))
        val minY = map.getInt(MineMeConstants.MINIMUM_Y_KEY)
        val maxY = map.getInt(MineMeConstants.MAXIMUM_Y_KEY)
        return CylinderRegion(Vector(center.x, 0.0, center.y), radius.toWE(), minY, maxY)
    }

    override fun serializeRegion(): Map<String, Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}