package me.ddevil.mineme.api.request

import me.ddevil.json.JsonObject
import me.ddevil.mineme.api.composition.MineComposition
import java.util.*

class MineCompositionRequest : SearchRequest<MineComposition> {


    companion object {
        const val COMPOSITION_SEARCH_URL = "composition"
    }

    constructor(name: String) : super(name, COMPOSITION_SEARCH_URL)

    constructor(uuid: UUID) : super(uuid, COMPOSITION_SEARCH_URL)


    override fun parse(json: JsonObject) = MineComposition(json)
}


