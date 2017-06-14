package me.ddevil.mineme.craft.config

import me.ddevil.shiroi.craft.config.FileConfigSource


enum class MineMeConfigSource
constructor(override val folderPath: String, override val resourcePath: String = folderPath) : FileConfigSource {
    MAIN("config.yml"),
    MESSAGES("assets/config/lang.yml"),
    GUI("assets/config/gui.yml");

}