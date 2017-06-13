package me.ddevil.mineme.craft.mine.loader

import com.sk89q.worldedit.regions.Polygonal2DRegion
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineType
import me.ddevil.mineme.craft.mine.PolygonalMine

class PolygonalLoader(plugin: MineMe) : AbstractLoader<Polygonal2DRegion>(plugin) {
    override val regionClass = Polygonal2DRegion::class.java
    override val supportedType = MineType.POLYGON

    override fun loadMine(map: Map<String, Any>) = PolygonalMine(plugin, map)

    override fun createMine(name: String, alias: String, composition: MineComposition,
                            region: Polygonal2DRegion): Mine {
        return PolygonalMine(plugin, alias, name, composition, region)
    }
}