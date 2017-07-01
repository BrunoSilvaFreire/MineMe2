package me.ddevil.mineme.craft.command

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.message.MineMeLang
import me.ddevil.shiroi.craft.command.Command
import me.ddevil.shiroi.craft.command.CommandArgs
import me.ddevil.shiroi.craft.misc.variable.MessageVariable
import me.ddevil.shiroi.craft.misc.variable.translateVariables

const val ALL_TARGET_IDENTIFIER = "all"

class GeneralCommands(plugin: MineMe) : MineMeCommand(plugin) {
    @Command(name = "reset", permission = "mineme.reset", usage = "/mm reset {mineName/all}")
    fun resetMine(args: CommandArgs) {
        val sender = args.sender
        args.getStringOrMessage(0, MineMeLang.COMMAND_RESET_MINE_REQUIRED, messageManager) {
            name ->
            if (name == ALL_TARGET_IDENTIFIER) {
                resetAllMines()
                plugin.messageManager.sendMessage(sender, MineMeLang.COMMAND_RESET_ALL_MINES_SUCCESS)
                return
            }
            val mine = plugin.mineManager.getMine(name)
            if (mine == null) {
                plugin.messageManager.sendMessage(sender,
                        MineMeLang.MINE_NOT_FOUND,
                        MessageVariable("name", name)
                )
                args.sendUsage()
                return
            }
            mine.reset()
            plugin.messageManager.sendMessage(
                    sender,
                    MineMeLang.COMMAND_RESET_MINE_SUCCESS,
                    *mine.exportVariables()

            )
        }

    }

    private fun resetAllMines() {
        for (mine in plugin.mineManager.mines) {
            mine.reset()
        }
    }

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
                    *mine.exportVariables()
            )

        }
    }

    @Command(name = "info", usage = "/mm info {mine}")
    fun mineInfo(args: CommandArgs) {
        val sender = args.sender
        args.getStringOrMessage(0, MineMeLang.COMMAND_CLEAR_MINE_REQUIRED, messageManager) {
            name ->
            val mine = plugin.mineManager.getMine(name)
            if (mine == null) {
                plugin.messageManager.sendMessage(sender,
                        MineMeLang.MINE_NOT_FOUND,
                        MessageVariable("name", name)
                )
                args.sendUsage()
                return
            }
            val lines = plugin.configManager.getValue(MineMeConfigValue.COMMAND_MINE_INFO)
            val variables = mine.exportVariables()
            for (line in lines) {
                plugin.messageManager.sendMessage(sender, translateVariables(line, *variables))
            }
        }
    }

    @Command(name = "clear", usage = "/mm clear {mineName/all}")
    fun clearMine(args: CommandArgs) {
        val sender = args.sender
        args.getStringOrMessage(0, MineMeLang.COMMAND_CLEAR_MINE_REQUIRED, messageManager) {
            name ->
            if (name == ALL_TARGET_IDENTIFIER) {
                clearAllMines()
                plugin.messageManager.sendMessage(sender, MineMeLang.COMMAND_CLEAR_ALL_MINES_SUCCESS)
                return
            }
            val mine = plugin.mineManager.getMine(name)
            if (mine == null) {
                plugin.messageManager.sendMessage(sender,
                        MineMeLang.MINE_NOT_FOUND,
                        MessageVariable("name", name)
                )
                return
            }
            mine.clear()
            plugin.messageManager.sendMessage(
                    sender,
                    MineMeLang.COMMAND_CLEAR_MINE_SUCCESS,
                    *mine.exportVariables()
            )
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

    private fun clearAllMines() {
        for (mine in plugin.mineManager.mines) {
            mine.clear()
        }
    }
}