package me.ddevil.mineme.craft.hologram.provider.holographicdisplays

import com.gmail.filoghost.holographicdisplays.`object`.CraftHologram
import com.gmail.filoghost.holographicdisplays.`object`.line.CraftTextLine
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.hologram.Hologram
import me.ddevil.util.emptyString
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

    override fun setLines(hologramText: List<String>) {
        holo.ensureLines(hologramText.size)
        for ((index, text) in hologramText.withIndex()) {
            val line = holo.getLine(index)
            (line as CraftTextLine).text = text
        }
    }

    override fun delete() {
        holo.delete()
    }


    private var holo: CraftHologram

    constructor(plugin: MineMe, location: Location) {
        this.holo = HologramsAPI.createHologram(plugin, location) as CraftHologram
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
