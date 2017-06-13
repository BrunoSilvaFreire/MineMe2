package me.ddevil.mineme.craft.mine

import com.google.common.collect.ImmutableMap
import com.sk89q.worldedit.Vector
import com.sk89q.worldedit.regions.CuboidRegion
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.hologram.formation.HologramFormation
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.mineme.craft.api.mine.MineType
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.util.getOrException
import org.bukkit.inventory.ItemStack

class CuboidMine : AbstractHologramMine<CuboidRegion> {
    constructor(plugin: MineMe,
                alias: String,
                name: String,
                composition: MineComposition,
                region: CuboidRegion,
                icon: ItemStack = plugin.mineManager.defaultMineIcon,
                defaultRepopulator: MineRepopulator = plugin.mineManager.defaultRepopulator,
                resetDelay: Int = plugin.configManager.getValue(MineMeConfigValue.DEFAULT_MINE_RESET_DELAY),
                formation: HologramFormation? = null,
                useCustomHologramText: Boolean = false,
                customHologramText: List<String> = emptyList()
    ) : super(plugin, alias, name, composition, region, icon, defaultRepopulator, resetDelay, formation,
            useCustomHologramText, customHologramText)

    constructor(plugin: MineMe, map: Map<String, Any>) : super(plugin, map)

    override fun loadRegion(map: Map<String, Any>): CuboidRegion {
        val x1 = map.getOrException<Int>("x1")
        val y1 = map.getOrException<Int>("y1")
        val z1 = map.getOrException<Int>("z1")
        val x2 = map.getOrException<Int>("x2")
        val y2 = map.getOrException<Int>("y2")
        val z2 = map.getOrException<Int>("z2")
        return CuboidRegion(Vector(x1, y1, z1), Vector(x2, y2, z2))
    }

    override val type: MineType
        get() = MineType.CUBOID

    override fun serializeRegion(): Map<String, Any> {
        val pos1 = region.pos1
        val pos2 = region.pos2
        return ImmutableMap.builder<String, Any>()
                .put("x1", pos1.blockX)
                .put("y1", pos1.blockY)
                .put("z1", pos1.blockZ)
                .put("x2", pos2.blockX)
                .put("y2", pos2.blockY)
                .put("z2", pos2.blockZ)
                .build()
    }
}
