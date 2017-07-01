package me.ddevil.mineme.craft.util

import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineMaterial
import me.ddevil.shiroi.craft.misc.variable.MessageVariable
import me.ddevil.shiroi.craft.util.toBukkit
import me.ddevil.shiroi.util.DEFAULT_SHIROI_ITEM_DATA_IDENTIFIER
import me.ddevil.shiroi.util.DEFAULT_SHIROI_ITEM_TYPE_IDENTIFIER
import org.bukkit.inventory.ItemStack

fun MineMaterial.toItemStack(): ItemStack {
    return ItemStack(material.toBukkit(), 1, data.toShort())
}

fun MineMaterial.exportVariables() = arrayOf(
        MessageVariable(MineMeConstants.MINE_MATERIAL_PERCENTAGE_IDENTIFIER, percentage.toString()),
        MessageVariable(DEFAULT_SHIROI_ITEM_TYPE_IDENTIFIER, material.name),
        MessageVariable(DEFAULT_SHIROI_ITEM_DATA_IDENTIFIER, data.toString())
)
