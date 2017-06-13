package me.ddevil.mineme.craft.mine.config

import me.ddevil.shiroi.craft.util.toMap
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

open class AbstractMineConfig(override val file: File) : MineConfig {
    override val map = try {
        YamlConfiguration.loadConfiguration(file).toMap()
    } catch (ex: Exception) {
        null
    }

}