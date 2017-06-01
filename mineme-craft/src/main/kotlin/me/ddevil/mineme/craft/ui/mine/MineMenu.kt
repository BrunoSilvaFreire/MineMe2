package me.ddevil.mineme.craft.ui.mine

import com.google.common.collect.ImmutableList
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.ui.UIResources
import me.ddevil.mineme.craft.util.toItemStack
import me.ddevil.shiroi.craft.util.ShiroiItemBuilder
import me.ddevil.shiroi.craft.util.toBukkit
import me.ddevil.shiroi.ui.api.UIPosition
import me.ddevil.shiroi.ui.api.component.Drawable
import me.ddevil.shiroi.ui.api.component.container.ArrayContainer
import me.ddevil.shiroi.ui.api.component.container.MenuSize
import me.ddevil.shiroi.ui.api.component.misc.ClickableItemSlotComponent
import me.ddevil.shiroi.ui.api.component.misc.value.ContainerValueModifier
import me.ddevil.shiroi.ui.api.component.misc.value.OnValueModifiedListener
import me.ddevil.shiroi.ui.api.event.UIClickEvent
import me.ddevil.shiroi.ui.api.misc.Action
import me.ddevil.shiroi.ui.shiroi.ShiroiMenu
import me.ddevil.shiroi.util.misc.item.Material
import org.bukkit.inventory.ItemStack
import java.text.DecimalFormat

class MineMenu
constructor(plugin: MineMe, val mine: Mine)
    : ShiroiMenu<MineMe>(plugin,
        plugin.messageManager.translateAll("$1${mine.name}$3($2${mine.alias}$3)"),
        MenuSize.SIX_ROWS,
        UIResources.PRIMARY_BACKGROUND) {
    companion object {
        val DECIMAL_FORMAT = DecimalFormat(",###.##")
        @JvmStatic
        fun main(args: Array<String>) {
            println(DECIMAL_FORMAT.format(35.1230))
        }
    }

    private val mainButtonsContainer: ArrayContainer<Drawable> = ArrayContainer(Drawable::class.java,
            4,
            3,
            UIResources.SECONDARY_BACKGROUND)
    private val compositionSelector: ContainerValueModifier<MineComposition>

    init {
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
                    }
                }
        ), 1, 0)
        //Clear button
        mainButtonsContainer.place(ClickableItemSlotComponent(UIResources.CLEAR_BUTTON, null,
                object : Action {
                    override fun invoke(e: UIClickEvent, localPosition: UIPosition) {
                        mine.clear()
                    }
                }
        ), 2, 0)
        place(mainButtonsContainer, 0, 0)
        val listener = object : OnValueModifiedListener<MineComposition> {
            override fun onModified(modificationValue: MineComposition, e: UIClickEvent, localPosition: UIPosition) {
                if (mine.composition == modificationValue) {
                    return
                }
                mine.composition = modificationValue
                if (e.isRightClick) {
                    mine.reset()
                }
            }
        }
        val populators = object : ContainerValueModifier.Populator<MineComposition> {
            private val iterator: Iterator<MineComposition> = plugin.mineManager.compositions.iterator()

            override fun get(x: Int, y: Int): MineComposition? {
                return if (iterator.hasNext()) {
                    iterator.next()
                } else {
                    null
                }
            }

        }
        val itemCreator = {
            comp: MineComposition, x: Int, y: Int ->
            val filter = plugin.configManager.getValue(MineMeConfigValue.COMMON_MATERIALS).map {
                Material.matchMaterial(it) ?: throw IllegalStateException("Unknown material $it")
            }.toSet()
            comp.largestMaterial(filter).toItemStack()
        }
        val updater = {
            composition: MineComposition, x: Int, y: Int, i: ItemStack ->
            val lore = ImmutableList.builder<String>()
                    .addAll(
                            composition.compositionMap.map {
                                return@map it.toColorizedString(DECIMAL_FORMAT)
                            }.toList())
                    .add("$2Left Click $3simply changes")
                    .add("$3 the selected composition")
                    .add("")
                    .add("$2Right Click $3changes the")
                    .add("$3composition and resets the mine")
            if (mine.composition == composition) {
                lore.add("$5Currently selected!")
            }
            ShiroiItemBuilder(plugin.messageManager, composition.randomMaterial().material.toBukkit())
                    .setName("$1${composition.name} $3($2${composition.alias}$3)")
                    .setLore(lore.build())
                    .build()
        }
        val options = ContainerValueModifier.Options(listener, populators, itemCreator, updater)
        compositionSelector = ContainerValueModifier(7, 1, options, UIResources.SECONDARY_BACKGROUND)
        place(compositionSelector, 1, 4)
    }
}