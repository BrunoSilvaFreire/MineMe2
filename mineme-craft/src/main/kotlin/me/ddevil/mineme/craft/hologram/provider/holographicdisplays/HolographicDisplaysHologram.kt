package me.ddevil.mineme.craft.hologram.provider.holographicdisplays

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.hologram.Hologram
import org.bukkit.Location

class HolographicDisplaysHologram : Hologram {
    override var location: Location
        get() = holo.location
        set(value) {
            holo.teleport(value)
        }
    override var visible: Boolean
        get() = holo.visibilityManager.isVisibleByDefault
        set(value) {
            holo.visibilityManager.isVisibleByDefault = value
        }

    override fun delete() {
        holo.delete()
    }


    private var holo: com.gmail.filoghost.holographicdisplays.api.Hologram

    constructor(plugin: MineMe, location: Location) {
        this.holo = HologramsAPI.createHologram(plugin, location)
    }

}