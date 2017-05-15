package me.ddevil.mineme.craft.command

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.message.MineMeMessageManager
import me.ddevil.shiroi.craft.command.ShiroiCommand

open class MineMeCommand(plugin: MineMe) : ShiroiCommand<MineMe>(plugin) {

    val messageManager: MineMeMessageManager

    init {
        this.messageManager = plugin.messageManager
    }
}