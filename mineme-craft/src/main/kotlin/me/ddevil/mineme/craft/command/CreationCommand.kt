package me.ddevil.mineme.craft.command

import com.sk89q.worldedit.bukkit.WorldEditPlugin
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.message.MineMeLang
import me.ddevil.shiroi.craft.command.Command
import me.ddevil.shiroi.craft.command.CommandArgs
import me.ddevil.shiroi.craft.misc.variable.MessageVariable
class CreationCommand
constructor(
        plugin: MineMe,
        private val worldEdit: WorldEditPlugin
) : MineMeCommand(plugin) {


    @Command(name = "create.mine", permission = "mineme.create.mine", inGameOnly = true)
    fun createMine(args: CommandArgs) {
        val player = args.player

        //Validate selection
        val selection = worldEdit.getSelection(player)
        if (selection == null) {
            messageManager.sendMessage(player, MineMeLang.COMMAND_MINE_CREATE_WORLD_EDIT_SELECTION_REQUIRED)
            return
        }
        if (!selection.regionSelector.isDefined) {
            messageManager.sendMessage(player, MineMeLang.COMMAND_MINE_CREATE_WORLD_EDIT_SELECTION_NOT_COMPLETE)
            return
        }
        val region = selection.regionSelector.region
        val loader = plugin.mineManager.getLoader(region.javaClass)
        if (loader == null) {
            messageManager.sendMessage(player, MineMeLang.COMMAND_MINE_CREATE_SELECTION_UNSUPPORTED,
                    MessageVariable("type", selection.javaClass.simpleName)
            )
            return
        }

        args.getStringOrElse(0, {
            //No name provided
            messageManager.sendMessage(player, MineMeLang.COMMAND_MINE_CREATE_NAME_REQUIRED)

        }) { name ->
            args.getStringOrElse(1, {
                //No alias provided
                messageManager.sendMessage(player, MineMeLang.COMMAND_MINE_CREATE_ALIAS_REQUIRED)

            }) { alias ->
                args.getStringOrElse(2, {
                    //No composition provided
                    messageManager.sendMessage(player, MineMeLang.COMMAND_MINE_CREATE_COMPOSITION_REQUIRED)
                }) { compositionName ->
                    val composition = plugin.mineManager.getComposition(compositionName)
                    if (composition == null) {
                        messageManager.sendMessage(player, MineMeLang.COMPOSITION_NOT_FOUND,
                                MessageVariable("name", compositionName)
                        )
                        return
                    }

                    //Create and register mine
                    val mine = loader.createMine(name, alias, composition, region)
                    plugin.mineManager.registerMine(mine)
                    mine.enabled = true
                    mine.reset()
                    mine.counting = true
                    messageManager.sendMessage(player, MineMeLang.COMMAND_MINE_CREATE_SUCCESSFUL,
                            MessageVariable("name", mine.name),
                            MessageVariable("alias", mine.alias),
                            MessageVariable("type", mine.type.name)
                    )
                }
            }
        }
    }

    @Command(name = "create.composition", permission = "mineme.create.composition")
    fun createComposition(args: CommandArgs) {
        val sender = args.sender
        args.getStringOrElse(0, {
            messageManager.sendMessage(sender, MineMeLang.COMMAND_COMPOSITION_CREATE_NAME_REQUIRED)
        }) { name ->
            args.getStringOrElse(1, {
                messageManager.sendMessage(sender, MineMeLang.COMMAND_COMPOSITION_CREATE_ALIAS_REQUIRED)
            }) { alias ->
                args.joinFromAnd(2) {
                    description ->
                    val composition = MineComposition(name, alias, listOf(description))
                    plugin.mineManager.registerMineComposition(composition)
                    messageManager.sendMessage(sender, MineMeLang.COMMAND_COMPOSITION_CREATE_SUCCESSFUL,
                            MessageVariable("name", composition.name),
                            MessageVariable("alias", composition.alias))
                }
            }
        }
    }
}