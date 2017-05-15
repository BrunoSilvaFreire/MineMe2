package me.ddevil.mineme.craft.mine.misc

import me.ddevil.mineme.craft.api.mine.Mine
import org.bukkit.scheduler.BukkitRunnable

class CountdownRunnable(val mine: Mine) : BukkitRunnable() {
    override fun run() {
        if (mine.currentCountdown <= 0) {
            mine.reset()
            mine.currentCountdown = mine.resetDelay
        } else {
            mine.currentCountdown--
        }
    }

}