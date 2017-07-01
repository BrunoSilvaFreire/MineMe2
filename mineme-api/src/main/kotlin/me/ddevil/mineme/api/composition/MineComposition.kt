package me.ddevil.mineme.api.composition

import me.ddevil.json.JsonObject
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.shiroi.util.misc.item.Material
import me.ddevil.util.*
import me.ddevil.util.misc.AbstractNameableDescribable
import java.util.*

open class MineComposition : AbstractNameableDescribable, Serializable {


    companion object {
        private val random = Random()
    }

    private val internalCompositionMap: MutableList<MineMaterial>

    @JvmOverloads
    constructor(name: String,
                alias: String,
                description: List<String>,
                compositionMap: List<MineMaterial> = emptyList()) : super(name, alias, description) {
        this.internalCompositionMap = ArrayList(compositionMap)
    }

    constructor(json: JsonObject) : this(json as Map<String, Any>)

    constructor(map: Map<String, Any>) : super(map,
            DEFAULT_NAME_IDENTIFIER,
            DEFAULT_ALIAS_IDENTIFIER,
            DEFAULT_DESCRIPTION_IDENTIFIER) {
        this.internalCompositionMap = ArrayList()
        val serializedMaterials = map.getList <Map<String, Any>>(MineMeConstants.MINE_COMPOSITION_MATERIALS_IDENTIFIER)
        serializedMaterials.mapTo(internalCompositionMap, ::MineMaterial)
    }

    val isEmpty: Boolean
        get() = internalCompositionMap.isEmpty()

    val compositionMap: List<MineMaterial>
        get() = ArrayList(internalCompositionMap)

    open val isRegistered get() = false

    fun add(material: MineMaterial) {
        internalCompositionMap.add(material)
        sortList()
    }


    fun remove(material: MineMaterial) {
        internalCompositionMap.remove(material)
        sortList()
    }

    fun randomMaterial(): MineMaterial {
        if (isEmpty) {
            return MineMaterial.EMPTY
        }
        return compositionMap[random.nextInt(compositionMap.size)]
    }

    fun largestMaterial(): MineMaterial {
        return largestMaterial(emptySet())
    }

    fun largestMaterial(filter: Set<Material>): MineMaterial {
        val possibleMaterials = compositionMap.filter { !filter.contains(it.material) }
        val toIterate = if (possibleMaterials.isNotEmpty()) {
            possibleMaterials
        } else {
            compositionMap
        }
        return toIterate.max() ?: throw IllegalStateException("There are no materials in the composition!")
    }

    override fun serialize(): Map<String, Any> = immutableMap {
        this[DEFAULT_NAME_IDENTIFIER] = name
        this[DEFAULT_ALIAS_IDENTIFIER] = alias
        this[DEFAULT_DESCRIPTION_IDENTIFIER] = description
        this[MineMeConstants.MINE_COMPOSITION_MATERIALS_IDENTIFIER] = compositionMap.map(MineMaterial::serialize)
    }

    private fun sortList() {
        Collections.sort(compositionMap)
    }

    fun serializeIndex(): Map<String, Any> = immutableMap {
        this[MineMeConstants.ID_IDENTIFIER] = name
        this[MineMeConstants.REGISTERED_IDENTIFIER] = isRegistered
    }

    operator fun contains(material: MineMaterial) = material in compositionMap

}