package me.ddevil.mineme.craft.ui

import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.ui.composition.CompositionMenu
import me.ddevil.mineme.craft.ui.mine.MineMenu
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.api.composition.MineMaterial
import me.ddevil.shiroi.util.misc.Reloadable
import me.ddevil.shiroi.util.misc.Toggleable
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*
import kotlin.properties.Delegates

class MineMeUIManager(val plugin: MineMe) : Toggleable, Reloadable {
    var mainMenu: MainMenu by Delegates.notNull<MainMenu>()
        private set
    private var mineMenuCache: MutableMap<Mine, MineMenu> = HashMap()

    private var compositionMenuCache: MutableMap<MineComposition, CompositionMenu> = HashMap()
    fun getMineMenu(mine: Mine) = mineMenuCache.getOrPut(mine) {
        val menu = MineMenu(plugin, mine)
        menu.register()
        return@getOrPut menu
    }

    fun getCompositionMenu(composition: MineComposition) = compositionMenuCache.getOrPut(composition){
        val menu = CompositionMenu(plugin, composition)
        menu.register()
        return@getOrPut menu
    }
    override fun disable() {

    }

    override fun reload() {
        loadItems()
    }

    override fun enable() {
        //fixme
        loadItems()
    }

    private fun loadItems() {
        UIResources.loadItems(plugin.configManager, plugin.messageManager)
        mainMenu = MainMenu(plugin, plugin.configManager.getValue(MineMeConfigValue.PLUGIN_PREFIX))
        mainMenu.register()
    }

    fun getBiggestMaterial(composition: MineComposition): ItemStack {
        if (composition.isEmpty) {
            return ItemStack(Material.STONE)
        }
        val map = composition.compositionMap
        val commonMaterials = plugin.configManager.getValue(MineMeConfigValue.COMMON_MATERIALS).map { Material.getMaterial(it) }
        val possibleMaterials = map.filter { !commonMaterials.contains(Material.getMaterial(it.material.name)) }
        val highest: MineMaterial = possibleMaterials.max() ?: map.first()
        return ItemStack(highest.material.id, 1, highest.data.toShort())
    }

}