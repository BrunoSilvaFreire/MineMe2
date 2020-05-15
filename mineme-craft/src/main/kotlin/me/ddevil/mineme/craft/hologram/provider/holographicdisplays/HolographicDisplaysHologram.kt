package me.ddevil.mineme.craft.hologram.provider.holographicdisplays

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.util.emptyString
import org.bukkit.Location

class HolographicDisplaysHologram : Hologram {
    override var location: Location
        get() = holo.location
        set(value) {
            holo.location = value
        }
    override var visible: Boolean
        get() = holo.visible
        set(value) {
            holo.visible = value
        }

    override fun setLines(hologramText: List<String>) {
        holo.setLines(hologramText)
    }

    override fun delete() {
        holo.delete()
    }


    private var holo: Hologram

    constructor(plugin: MineMe, location: Location) {
        this.holo = HologramsAPI.createHologram(plugin, location) as Hologram
    }

}

private fun com.gmail.filoghost.holographicdisplays.api.Hologram.ensureLines(lines: Int) {
    val size = this.size()
    if (lines <= 0) {
        clearLines()
        return
    }
    if (size > 0 && size > lines) {
        val amountToRemove = size - lines
        for (i in 0..amountToRemove - 1) {
            removeLine(size() - 1)
        }
    } else if (size < lines) {
        val amountToAdd = lines - size
        for (i in 0..amountToAdd - 1) {
            this.appendTextLine(emptyString())
        }
    }
}
