package me.ddevil.mineme.craft.network

import me.ddevil.mineme.api.network.NetworkManager
import me.ddevil.mineme.api.request.MineMeRequest

class InternalNetworkManager : NetworkManager {
    override fun <R> requestAsync(request: MineMeRequest<R>, listener: (R) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <R> requestSync(request: MineMeRequest<R>): R {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}