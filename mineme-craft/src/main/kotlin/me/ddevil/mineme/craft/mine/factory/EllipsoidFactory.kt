package me.ddevil.mineme.craft.mine.factory

import com.sk89q.worldedit.regions.EllipsoidRegion
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineType
import me.ddevil.mineme.craft.mine.EllipsoidMine

class EllipsoidFactory(plugin: MineMe) : AbstractFactory<EllipsoidRegion>(plugin) {
    override val regionClass get() = EllipsoidRegion::class.java
    override val supportedType = MineType.ELLIPSOID

    override fun loadMine(map: Map<String, Any>): Mine {
        return EllipsoidMine(plugin, map)
    }

    override fun createMine(name: String, alias: String, composition: MineComposition, region: EllipsoidRegion): Mine {
        return EllipsoidMine(plugin, alias, name, composition, region)
    }
}