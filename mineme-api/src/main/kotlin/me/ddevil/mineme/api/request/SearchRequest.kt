package me.ddevil.mineme.api.request

import me.ddevil.json.JsonObject
import me.ddevil.json.parse.JsonParser
import org.apache.http.client.HttpClient
import java.util.*

abstract class SearchRequest<T> : MineMeRequest<T> {


    constructor(name: String, searchUrl: String) : this(NameSearchMethod(name, searchUrl))

    constructor(uuid: UUID, searchUrl: String) : this(UUIDSearchMethod(uuid,
            searchUrl))


    constructor(searchMethod: SearchMethod) {
        this.searchMethod = searchMethod
    }

    private val searchMethod: SearchMethod


    override fun execute(client: HttpClient): T {
        val request = searchMethod.createRequest()
        val content = client.execute(request).entity.content
        val json = JsonParser().parseObject(content)
        return parse(json)
    }

    abstract fun parse(json: JsonObject): T
}