package me.ddevil.mineme.api.mine

import me.ddevil.json.JsonObject
import me.ddevil.mineme.api.MineMeAPI
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.api.exception.CompositionNotFoundException
import me.ddevil.mineme.api.request.MineCompositionRequest
import me.ddevil.util.*
import me.ddevil.util.misc.AbstractNameableDescribable
import java.util.*

/**
 * Used for serialization and deserialization outside of bukkit
 */
class MineInfo : AbstractNameableDescribable, Serializable {

    var composition: MineComposition

    constructor(name: String,
                alias: String,
                description: List<String>,
                composition: MineComposition) : super(name, alias, description) {
        this.composition = composition
    }

    constructor(map: Map<String, Any>, api: MineMeAPI) : super(map,
            DEFAULT_NAME_IDENTIFIER,
            DEFAULT_ALIAS_IDENTIFIER,
            DEFAULT_DESCRIPTION_IDENTIFIER) {
        val index = map.getMapAny(MineMeConstants.MINE_COMPOSITION_IDENTIFIER)
        val registeredComposition = index.getBoolean(MineMeConstants.REGISTERED_IDENTIFIER)
        val id = index.getString(MineMeConstants.ID_IDENTIFIER)
        composition = if (registeredComposition) {
            val uuid = UUID.fromString(id)!!
            val request = MineCompositionRequest(uuid)
            api.networkManager.requestSync(request)
        } else {
            api.storageManager.getComposition(id) ?: throw CompositionNotFoundException(id)
        }
    }

    override fun serialize(): Map<String, Any> = immutableMap {
        this[DEFAULT_NAME_IDENTIFIER] = name
        this[DEFAULT_ALIAS_IDENTIFIER] = alias
        this[DEFAULT_DESCRIPTION_IDENTIFIER] = description
        this[MineMeConstants.MINE_COMPOSITION_IDENTIFIER] = composition.serializeIndex()
    }

    constructor(map: JsonObject, api: MineMeAPI) : this(map as Map<String, Any>, api)
}