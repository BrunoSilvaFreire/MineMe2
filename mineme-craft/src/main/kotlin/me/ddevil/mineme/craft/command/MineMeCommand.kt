package me.ddevil.mineme.craft.command

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.config.MineMeConfigSource
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.shiroi.craft.command.ShiroiCommand
import me.ddevil.shiroi.craft.message.lang.FileLangMessageManager

open class MineMeCommand(plugin: MineMe) : ShiroiCommand<MineMe>(plugin) {

    val messageManager = plugin.messageManager

}