package me.ddevil.mineme.api.composition

import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.shiroi.util.misc.item.Item
import me.ddevil.shiroi.util.misc.item.Material
import me.ddevil.util.getDouble
import me.ddevil.util.immutableMap
import me.ddevil.util.set
import java.text.DecimalFormat

class MineMaterial : Item, Comparable<MineMaterial> {
    companion object {
        fun fromString(material: String): MineMaterial {
            val splitMaterial = material.split('=')
            if (splitMaterial.size <= 1) {
                throw IllegalArgumentException("$material is not properly formatted!")
            }
            val percentage: Double
            val item = splitMaterial[0]
            try {
                percentage = splitMaterial[1].toDouble()
            } catch(e: NumberFormatException) {
                throw IllegalArgumentException("Malformed double '${splitMaterial[1]}' in serialized material '$item'!")
            }
            val type: Material
            if (item.contains(':')) {
                val split = item.split(':')
                val data: Byte
                if (split.size > 1) {
                    try {
                        data = split[1].toByte()
                    } catch (n: NumberFormatException) {
                        throw IllegalArgumentException("Malformed byte '${split[1]}' in serialized material '$item'!")
                    }
                } else {
                    data = 0
                }
                val materialName = split[0]
                type = Material.getMaterial(materialName) ?: throw IllegalArgumentException("Unknown material type $materialName!")
                return MineMaterial(type, percentage, data)
            } else {
                type = Material.getMaterial(item) ?: throw IllegalArgumentException("Unknown material type $item!")
                return MineMaterial(type, percentage)
            }
        }

        val EMPTY = MineMaterial(Material.AIR, 100.0)
    }

    var percentage: Double

    @JvmOverloads
    constructor(material: Material, percentage: Double, data: Byte = 0) : super(material, data) {
        this.percentage = percentage
    }

    constructor(map: Map<String, Any>) : super(map) {
        this.percentage = map.getDouble(MineMeConstants.MINE_MATERIAL_PERCENTAGE_IDENTIFIER)
    }

    override fun compareTo(other: MineMaterial) = percentage.compareTo(other.percentage)

    override fun serialize(): Map<String, Any> = immutableMap {
        putAll(super.serialize())
        this[MineMeConstants.MINE_MATERIAL_PERCENTAGE_IDENTIFIER] = percentage
    }

    fun toColorizedString(decimalFormat: DecimalFormat) = "$1$material$3:$2$data$3=$5${decimalFormat.format(percentage)}$3%"

}

