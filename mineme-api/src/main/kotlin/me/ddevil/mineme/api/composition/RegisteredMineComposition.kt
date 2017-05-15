package me.ddevil.mineme.api.composition

import me.ddevil.json.JsonObject
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.network.NetworkIdentifiable
import me.ddevil.util.getString
import java.util.*

class RegisteredMineComposition : MineComposition, NetworkIdentifiable {
    override val uuid: UUID

    constructor(json: JsonObject) : this(json as Map<String, Any>)

    constructor(map: Map<String, Any>) : super(map){
        this.uuid = UUID.fromString(map.getString(MineMeConstants.ID_IDENTIFIER))!!
    }

    override val isRegistered get() = true
}