package me.ddevil.mineme.craft.ui

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.config.MineMeConfigSource
import me.ddevil.mineme.craft.event.MineDisabledEvent
import me.ddevil.mineme.craft.message.MineMeLang
import me.ddevil.mineme.craft.mine.config.ValidMineConfig
import me.ddevil.mineme.craft.ui.composition.CompositionDisplay
import me.ddevil.mineme.craft.ui.mine.MineDisplay
import me.ddevil.mineme.craft.ui.mine.UnloadedMineDisplay
import me.ddevil.mineme.craft.util.exportMessageVariables
import me.ddevil.shiroi.craft.misc.variable.MessageVariable
import me.ddevil.shiroi.ui.api.UIPosition
import me.ddevil.shiroi.ui.api.component.container.MenuSize
import me.ddevil.shiroi.ui.api.component.scrollable.UnderPanelScrollable
import me.ddevil.shiroi.ui.api.event.UIClickEvent
import me.ddevil.shiroi.ui.api.misc.Action
import me.ddevil.shiroi.ui.shiroi.FileLangShiroiMenu
import me.ddevil.shiroi.ui.shiroi.ShiroiScrollerUpdater
import org.bukkit.Material
import org.bukkit.event.EventHandler

class MainMenu(plugin: MineMe) : FileLangShiroiMenu<MineMe, MineMeConfigSource>(
        plugin,
        MineMeLang.MAIN_MENU_TITLE.key,
        MenuSize.SIX_ROWS,
        UIResources.PRIMARY_BACKGROUND) {

    private val scrollableUpdater = ShiroiScrollerUpdater(Material.EMERALD, plugin)
    private val loadedMinesDisplay = UnderPanelScrollable(
            MineDisplay::class.java,
            this,
            4,
            3,
            scrollableUpdater)

    private val loadedCompositionsDisplay = UnderPanelScrollable(
            CompositionDisplay::class.java,
            this,
            4,
            3,
            scrollableUpdater)
    private val unloadedMinesDisplay = UnderPanelScrollable(
            UnloadedMineDisplay::class.java,
            this,
            9,
            3,
            scrollableUpdater)

    init {
        this.background = UIResources.PRIMARY_BACKGROUND
        this.loadedMinesDisplay.background = UIResources.SECONDARY_BACKGROUND
        this.loadedCompositionsDisplay.background = UIResources.SECONDARY_BACKGROUND
        this.unloadedMinesDisplay.background = UIResources.SECONDARY_BACKGROUND
        this.loadedMinesDisplay.lowPanelBackground = UIResources.PRIMARY_BACKGROUND
        this.loadedCompositionsDisplay.lowPanelBackground = UIResources.PRIMARY_BACKGROUND
        this.unloadedMinesDisplay.lowPanelBackground = UIResources.PRIMARY_BACKGROUND
        place(loadedMinesDisplay, 0, 0)
        place(loadedCompositionsDisplay, 5, 0)
        place(unloadedMinesDisplay, 0, 3)
    }

    override fun update0() {
        loadedMinesDisplay.clear()
        loadedCompositionsDisplay.clear()
        unloadedMinesDisplay.clear()
        for (unloadedMine in plugin.mineManager.findUnloadedMines()) {
            unloadedMinesDisplay.add(
                    UnloadedMineDisplay(unloadedMine, plugin.messageManager, object : Action {
                        override fun invoke(p1: UIClickEvent, p2: UIPosition) {
                            val sender = p1.player
                            if (unloadedMine is ValidMineConfig) {
                                val mine = unloadedMine.loadMine(plugin.mineManager)
                                plugin.messageManager.sendMessage(sender,
                                        MineMeLang.MINE_LOADED,
                                        *mine.exportMessageVariables())
                            } else {
                                plugin.messageManager.sendMessage(sender, MineMeLang.UNABLE_TO_LOAD_MINE,
                                        MessageVariable("name", unloadedMine.file.name)
                                )

                            }
                            update()
                        }
                    })
            )
        }
        for (mine in plugin.mineManager.mines) {
            loadedMinesDisplay.add(
                    MineDisplay(mine, plugin.messageManager, object : Action {
                        override fun invoke(e: UIClickEvent, localPosition: UIPosition) {
                            plugin.uiManager.getMineMenu(mine).open(e.player)
                        }

                    })
            )
        }
        for (composition in plugin.mineManager.compositions) {
            loadedCompositionsDisplay.add(
                    CompositionDisplay(composition, plugin, object : Action {
                        override fun invoke(e: UIClickEvent, localPosition: UIPosition) {
                            plugin.uiManager.getCompositionMenu(composition).open(e.player)
                        }
                    })
            )
        }
    }

    @EventHandler
    fun onMineDisabled(e: MineDisabledEvent) {
        update()
    }
}