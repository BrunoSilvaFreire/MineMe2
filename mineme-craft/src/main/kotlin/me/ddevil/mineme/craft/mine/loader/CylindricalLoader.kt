package me.ddevil.mineme.craft.mine.loader

import com.sk89q.worldedit.regions.CylinderRegion
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineType
import me.ddevil.mineme.craft.mine.CylindricalMine

class CylindricalLoader(plugin: MineMe) : AbstractLoader<CylinderRegion>(plugin) {
    override val regionClass get() = CylinderRegion::class.java
    override val supportedType = MineType.CYLINDRICAL

    override fun loadMine(map: Map<String, Any>): Mine {
        return CylindricalMine(plugin, map)
    }

    override fun createMine(name: String, alias: String, composition: MineComposition, region: CylinderRegion): Mine {
        return CylindricalMine(plugin, alias, name, composition, region)
    }
}