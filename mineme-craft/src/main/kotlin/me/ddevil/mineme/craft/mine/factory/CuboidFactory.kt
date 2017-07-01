package me.ddevil.mineme.craft.mine.factory

import com.sk89q.worldedit.regions.CuboidRegion
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineType
import me.ddevil.mineme.craft.mine.CuboidMine

class CuboidFactory(plugin: MineMe) : AbstractFactory<CuboidRegion>(plugin) {

    override val regionClass: Class<CuboidRegion>
        get() = CuboidRegion::class.java

    override val supportedType: MineType
        get() = MineType.CUBOID

    override fun loadMine(map: Map<String, Any>) = CuboidMine(plugin, map)

    override fun createMine(name: String, alias: String, composition: MineComposition, region: CuboidRegion): Mine {
        return CuboidMine(plugin, alias, name, composition, region)
    }

}