package me.ddevil.mineme.craft.ui.composition

import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.shiroi.craft.util.ShiroiItemBuilder
import me.ddevil.shiroi.ui.api.component.misc.ClickableItemSlotComponent
import me.ddevil.shiroi.ui.api.misc.Action
import me.ddevil.shiroi.ui.api.updater.ItemUpdater
import org.bukkit.inventory.ItemStack
import java.text.DecimalFormat

class CompositionDisplay
constructor(
        val composition: MineComposition,
        val plugin: MineMe,
        action: Action
) : ClickableItemSlotComponent(
        object : ItemUpdater {
            override fun update(oldItem: ItemStack): ItemStack {
                val lore = ArrayList<String>(
                        composition.compositionMap
                                .map { "$1${it.material}$3:$1${it.data}$3=$2${FORMAT.format(it.percentage * 100)}" }
                )
                lore.add("")
                lore.addAll(composition.description.map { "$3$it" })
                return ShiroiItemBuilder(plugin.messageManager, oldItem)
                        .setName("$1${composition.name}$3($2${composition.alias})")
                        .setLore(lore)
                        .build()
            }
        }, plugin.uiManager.getBiggestMaterial(composition),
        id = null,
        action = action) {
    companion object {
        private val FORMAT = DecimalFormat(",###.##")
    }
}