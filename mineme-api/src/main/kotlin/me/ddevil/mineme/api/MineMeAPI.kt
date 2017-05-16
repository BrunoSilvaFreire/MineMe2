package me.ddevil.mineme.api

import me.ddevil.mineme.api.network.NetworkManager
import me.ddevil.mineme.api.storage.StorageManager

class MineMeAPI {
    val storageManager: StorageManager
    val networkManager: NetworkManager

    constructor(storageLoader: (MineMeAPI) -> StorageManager, networkLoader: (MineMeAPI) -> NetworkManager) {
        this.networkManager = networkLoader(this)
        this.storageManager = storageLoader(this)
    }

    constructor(storageManager: StorageManager, networkManager: NetworkManager) {
        this.storageManager = storageManager
        this.networkManager = networkManager
    }

}