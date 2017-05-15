package me.ddevil.mineme.craft.command

import me.ddevil.mineme.craft.MineMe
import me.ddevil.shiroi.craft.command.Command
import me.ddevil.shiroi.craft.command.CommandArgs
import me.ddevil.shiroi.craft.command.ShiroiCommand

class UICommand(plugin: MineMe) : ShiroiCommand<MineMe>(plugin) {

    @Command(name = "ui", permission = "mineme.ui", inGameOnly = true)
    fun ui(args: CommandArgs) {
        plugin.uiManager.mainMenu.open(args.player)
    }
}