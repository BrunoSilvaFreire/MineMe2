package me.ddevil.mineme.craft.ui.composition

import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineMaterial
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.config.MineMeConfigSource
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.message.MineMeLang
import me.ddevil.mineme.craft.ui.UIResources
import me.ddevil.mineme.craft.util.provide
import me.ddevil.shiroi.craft.misc.variable.MessageVariable
import me.ddevil.shiroi.craft.misc.variable.translateVariables
import me.ddevil.shiroi.craft.util.ShiroiItemBuilder
import me.ddevil.shiroi.craft.util.toBukkit
import me.ddevil.shiroi.ui.api.component.BackButton
import me.ddevil.shiroi.ui.api.component.container.MenuSize
import me.ddevil.shiroi.ui.api.component.misc.value.ValueModifierUpdater
import me.ddevil.shiroi.ui.shiroi.FileLangShiroiMenu
import org.bukkit.inventory.ItemStack

class MaterialEditorMenu(plugin: MineMe,
                         menu: CompositionMenu,
                         val material: MineMaterial) : FileLangShiroiMenu<MineMe, MineMeConfigSource>(
        plugin,
        MineMeLang.MATERIAL_EDITOR_MENU_TITLE.key,
        MenuSize.SIX_ROWS,
        UIResources.PRIMARY_BACKGROUND) {

    /*val percentModifier = AreaValueModifier(
            7, 2, {
        a, b ->
        //Value selectors
        var value = if (a in 0..2) {
            100 / Math.pow(2.0, a.toDouble() + 1)
        } else {
            when (a) {
                3 -> 20.0
                4 -> 10.0
                5 -> 5.0
                6 -> 2.5
                else -> {
                    1.0
                }
            }
        }
        if (b > 0) {
            value = -value
        }
        return@AreaValueModifier value
    }, {
        //Geter
        return@AreaValueModifier material.percentage
    }, {
        //Setter
        material.percentage += it
    }, object : ValueModifierUpdater<Double> {
        fun name(double: Double) = translateVariables(
                plugin.configManager.getValue(MineMeConfigValue.MATERIAL_ICON_NAME),
                *(material.provide() + MessageVariable("value", serializeValue(double)))
        )

        private fun serializeValue(value: Double): String {
            val prefix = if (value < 0) {
                "$4"
            } else {
                "$5+"
            }
            return prefix + MineMeConstants.DECIMAL_FORMAT.format(value)
        }

        fun lore(double: Double) = translateVariables(
                plugin.configManager.getValue(MineMeConfigValue.MATERIAL_ICON_LORE),
                *(material.provide() + MessageVariable("value",  serializeValue(double)))

        )


        override fun update(itemStack: ItemStack, value: Double): ItemStack {
            return ShiroiItemBuilder(plugin.messageManager, material.toBukkit())
                    .setName(name(value))
                    .setLore(lore(value))
                    .build()
        }
    }, UIResources.DEFAULT_MATERIAL_ICON, UIResources.SECONDARY_BACKGROUND)
*/
    init {
//        place(percentModifier, 1, 1)
        place(BackButton(UIResources.BACK_BUTTON, menu), 0, 5)
    }
}