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
import me.ddevil.shiroi.ui.api.component.container.MenuSize
import me.ddevil.shiroi.ui.api.component.holder.HolderClickListener
import me.ddevil.shiroi.ui.api.component.misc.ItemSlotComponent
import me.ddevil.shiroi.ui.api.component.scrollable.UnderPanelScrollable
import me.ddevil.shiroi.ui.api.event.UIClickEvent
import me.ddevil.shiroi.ui.api.updater.ItemUpdater
import me.ddevil.shiroi.ui.shiroi.ShiroiMenu
import me.ddevil.shiroi.ui.shiroi.ShiroiScrollerUpdater
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class CompositionMenu(plugin: MineMe, val composition: MineComposition) : ShiroiMenu<MineMe>(plugin,
        "$1${composition.alias}",
        MenuSize.SIX_ROWS,
        UIResources.PRIMARY_BACKGROUND) {
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
}

class MineMaterialDisplay(
        val composition: MineComposition,
        val material: MineMaterial,
        val messageManager: MessageManager
) : ItemSlotComponent(
        object : ItemUpdater {
            override fun update(oldItem: ItemStack): ItemStack {
                return ShiroiItemBuilder(messageManager, material.toBukkit())
                        .setName(material.toColorizedString(MineMeConstants.DECIMAL_FORMAT))
                        .build()
            }
        }, material.toBukkit(), null)
