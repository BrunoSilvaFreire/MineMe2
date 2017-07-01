package me.ddevil.mineme.craft.ui.mine

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.message.MineMeLang
import me.ddevil.mineme.craft.ui.UIResources
import me.ddevil.shiroi.ui.api.UIPosition
import me.ddevil.shiroi.ui.api.component.BackButton
import me.ddevil.shiroi.ui.api.component.CloseButton
import me.ddevil.shiroi.ui.api.component.Drawable
import me.ddevil.shiroi.ui.api.component.container.ArrayContainer
import me.ddevil.shiroi.ui.api.component.container.MenuSize
import me.ddevil.shiroi.ui.api.component.misc.ClickableItemSlotComponent
import me.ddevil.shiroi.ui.api.component.misc.value.BooleanValueModifier
import me.ddevil.shiroi.ui.api.component.misc.value.ValueModifierUpdater
import me.ddevil.shiroi.ui.api.event.UIClickEvent
import me.ddevil.shiroi.ui.api.misc.Action
import me.ddevil.shiroi.ui.shiroi.ShiroiMenu
import org.bukkit.inventory.ItemStack

class MineMenu
constructor(plugin: MineMe, val mine: Mine)
    : ShiroiMenu<MineMe>(plugin,
        plugin.messageManager.translateAll("$1${mine.name}$3($2${mine.alias}$3)"),
        MenuSize.SIX_ROWS,
        UIResources.PRIMARY_BACKGROUND) {
    val compositionSelectorMenu = CompositionSelectorMenu(plugin, this)

    private val mainButtonsContainer: ArrayContainer<Drawable> = ArrayContainer(Drawable::class.java,
            9,
            5,
            UIResources.SECONDARY_BACKGROUND)

    init {
        compositionSelectorMenu.register()
        //Teleportation button
        mainButtonsContainer.place(ClickableItemSlotComponent(UIResources.TELEPORT_BUTTON, null,
                object : Action {
                    override fun invoke(e: UIClickEvent, localPosition: UIPosition) {
                        e.player.teleport(mine.topCenter)
                    }

                }
        ), 0, 0)

        //Reset button
        mainButtonsContainer.place(ClickableItemSlotComponent(UIResources.RESET_BUTTON, null,
                object : Action {
                    override fun invoke(e: UIClickEvent, localPosition: UIPosition) {
                        mine.reset()
                        plugin.messageManager.sendMessage(
                                e.player,
                                MineMeLang.COMMAND_RESET_MINE_SUCCESS,
                                *mine.exportVariables()

                        )
                    }
                }
        ), 1, 0)
        //Clear button
        mainButtonsContainer.place(ClickableItemSlotComponent(UIResources.CLEAR_BUTTON, null,
                object : Action {
                    override fun invoke(e: UIClickEvent, localPosition: UIPosition) {
                        mine.clear()
                        plugin.messageManager.sendMessage(
                                e.player,
                                MineMeLang.COMMAND_CLEAR_MINE_SUCCESS,
                                *mine.exportVariables()

                        )
                    }
                }
        ), 2, 0)
        //Disable button
        mainButtonsContainer.place(ClickableItemSlotComponent(UIResources.DISABLE_BUTTON, null,
                object : Action {
                    override fun invoke(e: UIClickEvent, localPosition: UIPosition) {
                        mine.disable()
                        val player = e.player
                        player.closeInventory()
                        plugin.messageManager.sendMessage(player,
                                MineMeLang.MINE_DISABLED,
                                *mine.exportVariables())

                    }
                }
        ), 0, 1)
        //Delete button
        mainButtonsContainer.place(ClickableItemSlotComponent(UIResources.DELETE_BUTTON, null,
                object : Action {
                    override fun invoke(e: UIClickEvent, localPosition: UIPosition) {
                        mine.delete()
                        val player = e.player
                        player.closeInventory()
                        plugin.messageManager.sendMessage(player,
                                MineMeLang.MINE_DELETED,
                                *mine.exportVariables())
                    }
                }
        ), 1, 1)
        //Resume/Pause Button
        mainButtonsContainer.place(createBooleanModifier(), 0, 2)
        mainButtonsContainer.place(ClickableItemSlotComponent(UIResources.CHANGE_COMPOSITION_BUTTON, null,
                object : Action {
                    override fun invoke(p1: UIClickEvent, p2: UIPosition) = compositionSelectorMenu.open(p1.player)
                }
        ), 2, 2)
        place(mainButtonsContainer, 0, 0)
        place(BackButton(UIResources.BACK_BUTTON, plugin.uiManager.mainMenu), 0, 5)
        place(CloseButton(UIResources.CLOSE_BUTTON), 8, 5)
    }

    private fun createBooleanModifier() = BooleanValueModifier(
            { return@BooleanValueModifier mine.counting },
            {
                mine.counting = it
                this@MineMenu.update()
            },
            countdownItem, createUpdater()
    )

    private val countdownItem get() = if (mine.counting) {
        UIResources.PAUSE_COUNTDOWN_BUTTON
    } else {
        UIResources.RESUME_COUNTDOWN_BUTTON
    }

    private fun createUpdater() = object : ValueModifierUpdater<Boolean> {
        override fun update(oldItem: ItemStack, selectedValue: Boolean) = countdownItem
    }

}