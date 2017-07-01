package me.ddevil.mineme.craft.ui.composition

import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.api.composition.MineMaterial
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.util.toMineMaterial
import me.ddevil.mineme.craft.ui.UIResources
import me.ddevil.shiroi.craft.message.MessageManager
import me.ddevil.shiroi.craft.util.ShiroiItemBuilder
import me.ddevil.shiroi.craft.util.toBukkit
import me.ddevil.shiroi.ui.api.UIPosition
import me.ddevil.shiroi.ui.api.component.container.MenuSize
import me.ddevil.shiroi.ui.api.component.holder.HolderClickListener
import me.ddevil.shiroi.ui.api.component.misc.ClickableItemSlotComponent
import me.ddevil.shiroi.ui.api.component.scrollable.UnderPanelScrollable
import me.ddevil.shiroi.ui.api.event.UIClickEvent
import me.ddevil.shiroi.ui.api.misc.Action
import me.ddevil.shiroi.ui.api.updater.ItemUpdater
import me.ddevil.shiroi.ui.shiroi.ShiroiMenu
import me.ddevil.shiroi.ui.shiroi.ShiroiScrollerUpdater
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class CompositionMenu(plugin: MineMe, val composition: MineComposition) : ShiroiMenu<MineMe>(plugin,
        "$1${composition.alias}",
        MenuSize.SIX_ROWS,
        UIResources.PRIMARY_BACKGROUND) {
    private val menuCache = HashMap<MineMaterial, MaterialEditorMenu>()
    private var materialDisplays = UnderPanelScrollable(
            MineMaterialDisplay::class.java, this, 9, 5,
            ShiroiScrollerUpdater(Material.EMERALD, plugin),
            UIResources.SECONDARY_BACKGROUND, UIResources.PRIMARY_BACKGROUND)

    init {
        materialDisplays.addListener(object : HolderClickListener<MineMaterialDisplay> {
            override fun onClick(drawable: MineMaterialDisplay?, event: UIClickEvent) {
                val item = event.player.itemOnCursor
                if (item != null) {
                    composition.add(item.toMineMaterial())
                }
            }

        })
        place(materialDisplays, 0, 0)
    }

    override fun update0() {
        materialDisplays.clear()
        for (material in composition.compositionMap) {
            val display = MineMaterialDisplay(composition, material, plugin.messageManager, object : Action {
                override fun invoke(p1: UIClickEvent, p2: UIPosition) {
                    getMenu(material)?.open(p1.player)
                }
            })
            materialDisplays.add(display)
        }
    }

    private fun getMenu(material: MineMaterial): MaterialEditorMenu? {
        if (material !in composition) {
            val menu = menuCache.remove(material)
            if (menu != null) {
                plugin.unregisterListener(menu)
            }
            return null
        }
        return menuCache.getOrPut(material) {
            val menu = MaterialEditorMenu(plugin, this, material)
            menu.register()
            return menu
        }
    }
}

class MineMaterialDisplay(
        val composition: MineComposition,
        val material: MineMaterial,
        val messageManager: MessageManager,
        action: Action
) : ClickableItemSlotComponent(
        object : ItemUpdater {
            override fun update(oldItem: ItemStack): ItemStack {
                return ShiroiItemBuilder(messageManager, material.toBukkit())
                        .setName(material.toColorizedString(MineMeConstants.DECIMAL_FORMAT))
                        .build()
            }
        }, material.toBukkit(), action, null)
