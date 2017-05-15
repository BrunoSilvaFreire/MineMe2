package me.ddevil.mineme.api.network

import me.ddevil.mineme.api.request.RequestListener
import me.ddevil.mineme.api.request.MineMeRequest

interface NetworkManager {

    fun <R> requestAsync(request: MineMeRequest<R>, listener: RequestListener<R>)

    fun <R> requestSync(request: MineMeRequest<R>): R

}