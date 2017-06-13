package me.ddevil.mineme.craft.mine

import com.sk89q.worldedit.regions.EllipsoidRegion
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormation
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.mineme.craft.api.mine.MineType
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.util.toShiroi
import me.ddevil.mineme.craft.util.toWE
import me.ddevil.util.getMap
import me.ddevil.util.immutableMap
import me.ddevil.util.set
import me.ddevil.util.vector.DoubleVector3
import org.bukkit.inventory.ItemStack

class EllipsoidMine : AbstractHologramMine<EllipsoidRegion> {

    override val type = MineType.ELLIPSOID

    constructor(plugin: MineMe,
                alias: String,
                name: String,
                composition: MineComposition,
                region: EllipsoidRegion,
                icon: ItemStack = plugin.mineManager.defaultMineIcon,
                defaultRepopulator: MineRepopulator = plugin.mineManager.defaultRepopulator,
                resetDelay: Int = plugin.configManager.getValue(MineMeConfigValue.DEFAULT_MINE_RESET_DELAY),
                formation: HologramFormation? = null,
                useCustomHologramText: Boolean = false,
                customHologramText: List<String> = emptyList()
    ) : super(plugin, alias, name, composition, region, icon, defaultRepopulator, resetDelay, formation,
            useCustomHologramText, customHologramText)

    constructor(plugin: MineMe, map: Map<String, Any>) : super(plugin, map)

    override fun loadRegion(map: Map<String, Any>): EllipsoidRegion {
        val center = DoubleVector3(map.getMap(MineMeConstants.CENTER_KEY))
        val radius = DoubleVector3(map.getMap(MineMeConstants.RADIUS_KEY))
        return EllipsoidRegion(center.toWE(), radius.toWE())
    }

    override fun serializeRegion(): Map<String, Any> = immutableMap {
        this[MineMeConstants.CENTER_KEY] = region.center.toShiroi().serialize()
        this[MineMeConstants.RADIUS_KEY] = region.radius.toShiroi().serialize()
    }
}

