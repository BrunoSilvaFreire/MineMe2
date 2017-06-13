package me.ddevil.mineme.craft.ui.mine

import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.shiroi.craft.message.MessageManager
import me.ddevil.shiroi.craft.util.ShiroiItemBuilder
import me.ddevil.shiroi.ui.api.component.misc.ClickableItemSlotComponent
import me.ddevil.shiroi.ui.api.misc.Action
import me.ddevil.shiroi.ui.api.updater.ItemUpdater
import org.bukkit.inventory.ItemStack

class MineDisplay(
        val mine: Mine,
        val messageManager: MessageManager,
        action: Action
) : ClickableItemSlotComponent(
        object : ItemUpdater {
            override fun update(oldItem: ItemStack): ItemStack {
                return ShiroiItemBuilder(messageManager, mine.icon)
                        .setName("$1${mine.alias}$3($2${mine.name}$3)")
                        .setLore(listOf(
                                "$3Type: $1${mine.type}"
                        )).build()
            }
        }, ItemStack(mine.icon), id = null, action = action)