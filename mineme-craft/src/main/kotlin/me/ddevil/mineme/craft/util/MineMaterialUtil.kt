package me.ddevil.mineme.craft.util

import me.ddevil.mineme.api.composition.MineMaterial
import me.ddevil.shiroi.craft.util.toBukkit
import org.bukkit.inventory.ItemStack

fun MineMaterial.toItemStack(): ItemStack {
    return ItemStack(material.toBukkit(), 1, data.toShort())
}