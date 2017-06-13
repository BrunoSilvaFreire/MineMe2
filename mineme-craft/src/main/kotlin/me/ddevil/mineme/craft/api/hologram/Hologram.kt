package me.ddevil.mineme.craft.api.hologram

import org.bukkit.Location

interface Hologram {

    var location: Location

    var visible: Boolean

    fun delete()

    fun setLines(hologramText: List<String>)
}