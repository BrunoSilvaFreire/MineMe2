package me.ddevil.mineme.craft.hologram

import me.ddevil.mineme.craft.api.hologram.Hologram
import org.bukkit.Location

interface HologramHandler {

    fun create(location: Location): Hologram

}