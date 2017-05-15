package me.ddevil.mineme.craft.message

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.config.MineMeConfigKey
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.shiroi.craft.message.FileLangMessageManager
import me.ddevil.shiroi.craft.message.lang.Lang

class MineMeMessageManager(plugin: MineMe) : FileLangMessageManager<
        MineMe,
        Lang<MineMeConfigValue<String>, MineMeConfigKey>,
        MineMeConfigKey,
        MineMeConfigValue<String>>(
        //Constructor
        plugin,
        plugin.configManager.getValue(MineMeConfigValue.MESSAGE_SEPARATOR),
        plugin.configManager.getValue(MineMeConfigValue.PLUGIN_PREFIX),
        listOf())