package me.ddevil.mineme.craft.ui.mine

import me.ddevil.mineme.craft.mine.config.InvalidMineConfig
import me.ddevil.mineme.craft.mine.config.MineConfig
import me.ddevil.mineme.craft.mine.config.ValidMineConfig
import me.ddevil.mineme.craft.ui.UIResources
import me.ddevil.shiroi.craft.message.MessageManager
import me.ddevil.shiroi.craft.util.ShiroiItemBuilder
import me.ddevil.shiroi.ui.api.component.misc.ClickableItemSlotComponent
import me.ddevil.shiroi.ui.api.misc.Action
import me.ddevil.shiroi.ui.api.updater.ItemUpdater
import me.ddevil.util.exception.ArgumentOutOfRangeException
import org.bukkit.inventory.ItemStack

class UnloadedMineDisplay(
        val mine: MineConfig,
        val messageManager: MessageManager,
        action: Action
) : ClickableItemSlotComponent(createUpdater(mine, messageManager), getIcon(mine), id = null, action = action)

fun getIcon(mine: MineConfig): ItemStack {
    if (mine is ValidMineConfig) {
        return mine.icon
    } else {
        return UIResources.INVALID_CONFIG_ICON
    }
}


private fun createUpdater(mine: MineConfig, messageManager: MessageManager): ItemUpdater {
    if (mine is ValidMineConfig) {
        return object : ItemUpdater {
            override fun update(oldItem: ItemStack): ItemStack {
                return ShiroiItemBuilder(messageManager, mine.icon)
                        .setName("$5${mine.file.name}")
                        .setLore(listOf(
                                "$1${mine.name}$3($2${mine.alias}$3)",
                                "$3World: $2${mine.world}",
                                "$3Type: $1${mine.type}",
                                "$5Click to load mine."
                        )).build()
            }
        }
    } else if (mine is InvalidMineConfig) {
        return object : ItemUpdater {
            override fun update(oldItem: ItemStack): ItemStack {
                return ShiroiItemBuilder(messageManager, UIResources.INVALID_CONFIG_ICON)
                        .setName("$4${mine.file.name}")
                        .setLore(getLore(mine))
                        .build()
            }

            private fun getLore(mine: InvalidMineConfig): List<String> {
                val lore = ArrayList<String>()
                lore += "$3Errors:"
                mine.missingFields.mapTo(lore) { "$3- $4$it" }
                return lore
            }
        }
    }
    throw ArgumentOutOfRangeException("mine")
}
