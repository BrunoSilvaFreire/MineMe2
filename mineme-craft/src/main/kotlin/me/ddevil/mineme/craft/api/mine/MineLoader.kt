package me.ddevil.mineme.craft.api.mine

import com.sk89q.worldedit.regions.Region
import me.ddevil.mineme.api.composition.MineComposition

interface MineLoader<R : Region> {

    val regionClass: Class<R>

    val supportedType: MineType

    fun loadMine(map: Map<String, Any>): Mine

    fun createMine(name: String, alias: String, composition: MineComposition, region: R): Mine
}