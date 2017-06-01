package me.ddevil.mineme.craft.network

import me.ddevil.mineme.api.network.NetworkManager
import me.ddevil.mineme.api.request.MineMeRequest
import me.ddevil.mineme.api.request.RequestListener
import java.lang.UnsupportedOperationException

class InternalNetworkManager : NetworkManager {
    override fun <R> requestAsync(request: MineMeRequest<R>, listener: RequestListener<R>) {
        throw UnsupportedOperationException("MineMeCentral is not implemented yet!")
    }

    override fun <R> requestSync(request: MineMeRequest<R>): R {
        throw UnsupportedOperationException("MineMeCentral is not implemented yet!")
    }

}