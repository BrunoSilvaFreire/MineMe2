package me.ddevil.mineme.craft.ui.mine

import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.config.MineMeConfigSource
import me.ddevil.mineme.craft.message.MineMeLang
import me.ddevil.mineme.craft.ui.UIResources
import me.ddevil.mineme.craft.ui.composition.CompositionDisplay
import me.ddevil.shiroi.ui.api.UIPosition
import me.ddevil.shiroi.ui.api.component.BackButton
import me.ddevil.shiroi.ui.api.component.CloseButton
import me.ddevil.shiroi.ui.api.component.container.ArrayContainer
import me.ddevil.shiroi.ui.api.component.container.MenuSize
import me.ddevil.shiroi.ui.api.event.UIClickEvent
import me.ddevil.shiroi.ui.api.misc.Action
import me.ddevil.shiroi.ui.shiroi.FileLangShiroiMenu

class CompositionSelectorMenu(plugin: MineMe, val mineMenu: MineMenu) : FileLangShiroiMenu<MineMe, MineMeConfigSource>(
        plugin,
        MineMeLang.COMPOSITION_SELECTOR_MENU_TITLE.key,
        MenuSize.SIX_ROWS,
        UIResources.PRIMARY_BACKGROUND,
        null) {
    val container = ArrayContainer(CompositionDisplay::class.java, 9, 5, UIResources.SECONDARY_BACKGROUND)
    val mine = mineMenu.mine

    init {
        place(container, 0, 0)
        place(BackButton(UIResources.BACK_BUTTON, mineMenu), 0, 5)
        place(CloseButton(UIResources.CLOSE_BUTTON), 8, 5)
    }

    override fun update0() {
        container.clear()
        for (composition in plugin.mineManager.compositions) {
            val display = CompositionDisplay(composition, plugin, object : Action {
                override fun invoke(p1: UIClickEvent, p2: UIPosition) {
                    mine.composition = composition
                    mine.save()
                    if (p1.isRightClick) {
                        mine.reset()
                    }
                }
            })
            container.add(display)
        }
    }
}