package me.ddevil.mineme.api

import me.ddevil.mineme.api.network.NetworkManager
import me.ddevil.mineme.api.storage.StorageManager

interface MineMeAPI {
    val storageManager: StorageManager
    val networkManager: NetworkManager
}