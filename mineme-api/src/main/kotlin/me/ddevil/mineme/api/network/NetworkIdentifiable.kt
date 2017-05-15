package me.ddevil.mineme.api.network

import me.ddevil.util.misc.Nameable
import java.util.*

interface NetworkIdentifiable : Nameable {

    val uuid: UUID

}