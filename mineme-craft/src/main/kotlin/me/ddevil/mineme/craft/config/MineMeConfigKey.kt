package me.ddevil.mineme.craft.config

import me.ddevil.shiroi.craft.config.FileConfigKey

enum class MineMeConfigKey
constructor(override val folderPath: String, override val resourcePath: String = folderPath) : FileConfigKey {
    MAIN("config.yml"),
    MESSAGES("lang.yml"),
    GUI("gui.yml");

}