package me.ddevil.mineme.api

import java.util.*

class MineMeUser {

    val name: String
    val uuid: UUID


    constructor(name: String, uuid: UUID) {
        this.name = name
        this.uuid = uuid
    }

}