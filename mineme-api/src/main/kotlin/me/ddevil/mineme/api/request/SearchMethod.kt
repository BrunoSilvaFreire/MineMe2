package me.ddevil.mineme.api.request

import me.ddevil.mineme.api.MineMeConstants
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpUriRequest
import java.util.*

interface SearchMethod {

    fun createRequest(): HttpUriRequest
}

class UUIDSearchMethod(val uuid: UUID, val searchUrl: String) : SearchMethod {
    override fun createRequest() = HttpGet("${MineMeConstants.API_BASE_URL}$searchUrl/uuid/$uuid")

}

class NameSearchMethod(val name: String, val searchUrl: String) : SearchMethod {
    override fun createRequest() = HttpGet("${MineMeConstants.API_BASE_URL}$searchUrl/name/$name")

}