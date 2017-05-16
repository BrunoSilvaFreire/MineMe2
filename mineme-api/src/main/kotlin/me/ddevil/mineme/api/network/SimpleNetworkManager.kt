package me.ddevil.mineme.api.network

import me.ddevil.mineme.api.request.MineMeRequest
import me.ddevil.mineme.api.request.RequestListener
import org.apache.http.impl.client.HttpClients

class SimpleNetworkManager : NetworkManager {
    val client = HttpClients.createMinimal()!!
    //todo: real proper async
    override fun <R> requestAsync(request: MineMeRequest<R>, listener: RequestListener<R>) {
        listener(request.execute(client))
    }

    override fun <R> requestSync(request: MineMeRequest<R>): R {
        return request.execute(client)
    }
}