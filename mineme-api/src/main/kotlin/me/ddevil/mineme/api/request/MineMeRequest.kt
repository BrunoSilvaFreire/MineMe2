package me.ddevil.mineme.api.request

import org.apache.http.client.HttpClient

interface MineMeRequest<out R> {

    fun execute(client: HttpClient): R
}

