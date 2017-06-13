package me.ddevil.mineme.craft.mine.config

import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineManager
import me.ddevil.mineme.craft.api.mine.MineType
import me.ddevil.shiroi.craft.util.toBukkit
import me.ddevil.util.getBoolean
import me.ddevil.util.getMap
import me.ddevil.util.getMapAny
import me.ddevil.util.getString
import org.bukkit.inventory.ItemStack
import java.io.File

typealias ShiroiItemStack = me.ddevil.shiroi.util.misc.item.ItemStack
class ValidMineConfig(file: File) : AbstractMineConfig(file) {

    val name: String
    val alias: String
    val icon: ItemStack
    val world: String
    val type: MineType
    val composition: String
    val enabled: Boolean
    val region: Map<String, Any>
    override val map: Map<String, Any>
        get() = super.map ?: throw IllegalStateException("Couldn't load map!")

    init {
        this.name = map.getString(MineMeConstants.MINE_NAME_IDENTIFIER)
        this.alias = map.getString(MineMeConstants.MINE_ALIAS_IDENTIFIER)
        this.icon = ShiroiItemStack(map.getMap(MineMeConstants.MINE_ICON_IDENTIFIER)).toBukkit()
        this.world = map.getString(MineMeConstants.MINE_WORLD_IDENTIFIER)
        this.type = MineType.valueOf(map.getString(MineMeConstants.MINE_TYPE_IDENTIFIER))
        this.composition = map.getString(MineMeConstants.MINE_COMPOSITION_IDENTIFIER)
        this.enabled = map.getBoolean(MineMeConstants.MINE_ENABLED_IDENTIFIER)
        this.region = map.getMapAny(MineMeConstants.MINE_REGION_IDENTIFIER)
    }

    fun loadMine(mineManager: MineManager): Mine {
        return mineManager.loadMine(this)
    }

}

