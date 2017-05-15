package me.ddevil.mineme.craft.api.util

import me.ddevil.mineme.api.composition.MineMaterial
import me.ddevil.shiroi.craft.toShiroi
import org.bukkit.inventory.ItemStack

fun ItemStack.toMineMaterial() = MineMaterial(type.toShiroi(), 0.0, data.data)
