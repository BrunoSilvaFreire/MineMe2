package me.ddevil.mineme.craft.config

import me.ddevil.mineme.craft.MineMe
import me.ddevil.shiroi.craft.config.SimpleFileConfigManager

class MineMeConfigManager(
        plugin: MineMe
) : SimpleFileConfigManager<MineMe, MineMeConfigKey>(plugin, MineMeConfigKey::class.java, MineMeConfigValue.COLOR_DESIGN)