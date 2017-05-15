package me.ddevil.mineme.craft.ui.mine

import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.message.MineMeMessageManager
import me.ddevil.shiroi.craft.util.ItemBuilder
import me.ddevil.shiroi.ui.api.misc.Action
import me.ddevil.shiroi.ui.api.updater.ItemUpdater
import me.ddevil.shiroi.ui.internal.component.misc.ClickableItemSlotComponent
import org.bukkit.inventory.ItemStack

class MineDisplay(val mine: Mine, val messageManager: MineMeMessageManager, action: Action) : ClickableItemSlotComponent(
        object : ItemUpdater {
            override fun update(oldItem: ItemStack): ItemStack {
                return ItemBuilder(mine.icon, messageManager)
                        .setName("$1${mine.name}$3($2${mine.alias}$3)")
                        .setLore(listOf(
                                "$3Type: $1${mine.type}"
                        )).toItemStack()
            }
        }, ItemStack(mine.icon), id = null, action = action) {

}