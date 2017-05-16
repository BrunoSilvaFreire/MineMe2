package me.ddevil.mineme.api.request

import me.ddevil.json.JsonObject
import me.ddevil.mineme.api.MineMeAPI
import me.ddevil.mineme.api.mine.MineInfo
import java.util.*

class MineInfoRequest : SearchRequest<MineInfo> {
    private val api: MineMeAPI

    override fun parse(json: JsonObject): MineInfo {
        return MineInfo(json, api)
    }

    companion object {
        const val MINE_SEARCH_URL = "mine"
    }

    constructor(name: String, api: MineMeAPI) : super(name, MINE_SEARCH_URL) {
        this.api = api
    }

    constructor(uuid: UUID, api: MineMeAPI) : super(uuid, MINE_SEARCH_URL) {
        this.api = api
    }
}