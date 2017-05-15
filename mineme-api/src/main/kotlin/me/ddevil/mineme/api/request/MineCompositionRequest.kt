package me.ddevil.mineme.api.request

import me.ddevil.json.parse.JsonParser
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpUriRequest
import java.util.*

class MineCompositionRequest : MineMeRequest<MineComposition> {


    class UUIDSearchMethod(val uuid: UUID) : MineCompositionRequest.SearchMethod {
        override fun createRequest() = HttpGet("${MineMeConstants.API_BASE_URL}composition/uuid/$uuid")

    }

    class NameSearchMethod(val name: String) : MineCompositionRequest.SearchMethod {
        override fun createRequest() = HttpGet("${MineMeConstants.API_BASE_URL}composition/name/$name")

    }

    interface SearchMethod {

        fun createRequest(): HttpUriRequest
    }

    constructor(name: String) : this(NameSearchMethod(name))

    constructor(uuid: UUID) : this(UUIDSearchMethod(uuid))

    private val searchMethod: SearchMethod

    constructor(searchMethod: SearchMethod) {
        this.searchMethod = searchMethod
    }

    override fun execute(client: HttpClient): MineComposition {
        val request = searchMethod.createRequest()
        val content = client.execute(request).entity.content
        val json = JsonParser().parseObject(content)
        return MineComposition(json)
    }
}
