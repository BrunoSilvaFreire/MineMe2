package me.ddevil.mineme.craft.api.hologram

import org.bukkit.Location

interface HologramManager {

    fun getFormation(string: String): HologramFormation?

    fun registerFormation(formation: HologramFormation)

    fun createHologram(location: Location): Hologram?

}