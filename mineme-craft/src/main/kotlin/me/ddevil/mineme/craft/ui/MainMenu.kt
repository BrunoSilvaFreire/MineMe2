package me.ddevil.mineme.craft.ui

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.ui.composition.CompositionDisplay
import me.ddevil.mineme.craft.ui.mine.MineDisplay
import me.ddevil.shiroi.ui.api.UIPosition
import me.ddevil.shiroi.ui.api.component.container.MenuSize
import me.ddevil.shiroi.ui.api.event.UIClickEvent
import me.ddevil.shiroi.ui.api.misc.Action
import me.ddevil.shiroi.ui.internal.component.scrollable.UnderPanelScrollable
import me.ddevil.shiroi.ui.shiroi.ShiroiMenu
import me.ddevil.shiroi.ui.shiroi.ShiroiScrollerUpdater
import org.bukkit.Material

class MainMenu(plugin: MineMe, title: String) : ShiroiMenu<MineMe>(
        plugin,
        title,
        MenuSize.SIX_ROWS,
        UIResources.PRIMARY_BACKGROUND) {

    private var loadedMinesDisplay = UnderPanelScrollable(
            MineDisplay::class.java,
            this,
            4,
            3,
            ShiroiScrollerUpdater(Material.EMERALD, plugin))

    private var loadedCompositionsDisplay = UnderPanelScrollable(
            CompositionDisplay::class.java,
            this,
            4,
            3,
            ShiroiScrollerUpdater(Material.EMERALD, plugin))

    init {

        background = UIResources.PRIMARY_BACKGROUND
        loadedMinesDisplay.background = UIResources.SECONDARY_BACKGROUND
        loadedCompositionsDisplay.background = UIResources.SECONDARY_BACKGROUND
        loadedMinesDisplay.lowPanelBackground = UIResources.PRIMARY_BACKGROUND
        loadedCompositionsDisplay.lowPanelBackground = UIResources.PRIMARY_BACKGROUND
        place(loadedMinesDisplay, 0, 0)
        place(loadedCompositionsDisplay, 5, 0)
    }

    override fun update0() {
        loadedMinesDisplay.clear()
        loadedCompositionsDisplay.clear()
        plugin.mineManager.mines.forEach {
            loadedMinesDisplay.add(
                    MineDisplay(it,
                            plugin.messageManager,
                            object : Action {
                                override fun invoke(e: UIClickEvent, localPosition: UIPosition) {
                                    plugin.uiManager.getMineMenu(it).open(e.player)
                                }

                            })
            )
        }
        plugin.mineManager.compositions.forEach {
            loadedCompositionsDisplay.add(
                    CompositionDisplay(it,
                            plugin,
                            object : Action {
                                override fun invoke(e: UIClickEvent, localPosition: UIPosition) {
                                    plugin.uiManager.getCompositionMenu(it).open(e.player)
                                }
                            })
            )
        }
    }
}