package me.ddevil.mineme.craft.command

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.message.MineMeLang
import me.ddevil.shiroi.craft.command.Command
import me.ddevil.shiroi.craft.command.CommandArgs
import me.ddevil.shiroi.craft.message.lang.MessageVariable

class ListCommand(plugin: MineMe) : MineMeCommand(plugin) {


    @Command(name = "list.mines", permission = "mineme.list.mines")
    fun listMines(args: CommandArgs) {
        val sender = args.sender
        val mines = plugin.mineManager.mines
        if (mines.isEmpty()) {
            messageManager.sendMessage(sender, MineMeLang.COMMAND_MINE_LIST_EMPTY)
            return
        }
        messageManager.sendMessage(sender, MineMeLang.COMMAND_MINE_LIST_HEADER)
        for (mine in mines) {
            messageManager.sendMessage(sender, MineMeLang.COMMAND_MINE_LIST_FOUND,
                    MessageVariable("name", mine.name),
                    MessageVariable("alias", mine.alias),
                    MessageVariable("type", mine.type.name),
                    MessageVariable("world", mine.world.name)
            )

        }
    }

    @Command(name = "list.compositions", permission = "mineme.list.compositions")
    fun listCompositions(args: CommandArgs) {
        val sender = args.sender
        val compositions = plugin.mineManager.compositions
        if (compositions.isEmpty()) {
            messageManager.sendMessage(sender, MineMeLang.COMMAND_COMPOSITION_LIST_EMPTY)
            return
        }
        messageManager.sendMessage(sender, MineMeLang.COMMAND_COMPOSITION_LIST_HEADER)
        for (composition in compositions) {
            messageManager.sendMessage(sender, MineMeLang.COMMAND_COMPOSITION_LIST_FOUND,
                    MessageVariable("name", composition.name),
                    MessageVariable("alias", composition.alias),
                    MessageVariable("empty", composition.isEmpty.toString())
            )

        }
    }

}