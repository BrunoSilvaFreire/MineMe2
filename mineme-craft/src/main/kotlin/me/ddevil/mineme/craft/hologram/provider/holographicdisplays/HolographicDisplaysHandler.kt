package me.ddevil.mineme.craft.hologram.provider.holographicdisplays


import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.mineme.craft.hologram.HologramHandler
import org.bukkit.Location

class HolographicDisplaysHandler(val plugin: MineMe) : HologramHandler {
    override fun create(location: Location): Hologram {
        return HolographicDisplaysHologram(plugin, location)
    }

}

