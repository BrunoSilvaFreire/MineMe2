package me.ddevil.mineme.craft.ui

import com.google.common.collect.ImmutableMap
import me.ddevil.mineme.craft.config.MineMeConfigKey
import me.ddevil.mineme.craft.config.MineMeConfigManager
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.message.MineMeMessageManager
import me.ddevil.shiroi.craft.util.createConfig
import me.ddevil.shiroi.craft.util.parseConfig
import me.ddevil.shiroi.util.ShiroiConstants
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import kotlin.properties.Delegates

object UIResources {

    var PRIMARY_BACKGROUND: ItemStack by Delegates.notNull<ItemStack>()
    var SECONDARY_BACKGROUND: ItemStack by Delegates.notNull<ItemStack>()
    var TELEPORT_BUTTON: ItemStack by Delegates.notNull<ItemStack>()
    var RESET_BUTTON: ItemStack by Delegates.notNull<ItemStack>()
    var CLEAR_BUTTON: ItemStack by Delegates.notNull<ItemStack>()

    fun loadItems(configManager: MineMeConfigManager, messageManager: MineMeMessageManager) {
        this.PRIMARY_BACKGROUND = parseConfig(configManager.getValue(Keys.PRIMARY_BACKGROUND), messageManager)
        this.SECONDARY_BACKGROUND = parseConfig(configManager.getValue(Keys.SECONDARY_BACKGROUND), messageManager)
        this.TELEPORT_BUTTON = parseConfig(configManager.getValue(Keys.TELEPORT_BUTTON), messageManager)
        this.RESET_BUTTON = parseConfig(configManager.getValue(Keys.RESET_BUTTON), messageManager)
        this.CLEAR_BUTTON = parseConfig(configManager.getValue(Keys.CLEAR_BUTTON), messageManager)
    }

    object Keys {
        val PRIMARY_BACKGROUND = MineMeConfigValue(
                createConfig(
                        ImmutableMap.of(
                                ShiroiConstants.MATERIAL_TYPE_IDENTIFIER, Material.IRON_FENCE.name,
                                ShiroiConstants.NAME_IDENTIFIER, "&r"
                        )
                ),
                MineMeConfigKey.GUI,
                "resources.primaryBackground"
        )
        val SECONDARY_BACKGROUND = MineMeConfigValue(
                createConfig(
                        ImmutableMap.of(
                                ShiroiConstants.MATERIAL_TYPE_IDENTIFIER, Material.STAINED_GLASS_PANE.name,
                                ShiroiConstants.ITEM_DATA_IDENTIFIER, 15,
                                ShiroiConstants.NAME_IDENTIFIER, "&r"
                        )
                ),
                MineMeConfigKey.GUI,
                "resources.secondaryBackground"
        )
        val TELEPORT_BUTTON = MineMeConfigValue(
                createConfig(
                        ImmutableMap.builder<String, Any>()
                                .put(ShiroiConstants.MATERIAL_TYPE_IDENTIFIER, Material.ENDER_PEARL.name)
                                .put(ShiroiConstants.NAME_IDENTIFIER, "$2Teleport to mine location!")
                                .build()
                ),
                MineMeConfigKey.GUI,
                "resources.teleportButton"
        )
        val RESET_BUTTON = MineMeConfigValue(
                createConfig(
                        ImmutableMap.builder<String, Any>()
                                .put(ShiroiConstants.MATERIAL_TYPE_IDENTIFIER, Material.IRON_PICKAXE.name)
                                .put(ShiroiConstants.NAME_IDENTIFIER, "$2Resets the mine!")
                                .put(ShiroiConstants.ITEM_LORE_IDENTIFIER, listOf(
                                        "$3Uses the default mine repopulator",
                                        "$3and executor to reset the mine."
                                ))
                                .build()
                ),
                MineMeConfigKey.GUI,
                "resources.resetButton"
        )

        val CLEAR_BUTTON = MineMeConfigValue(
                createConfig(
                        ImmutableMap.builder<String, Any>()
                                .put(ShiroiConstants.MATERIAL_TYPE_IDENTIFIER, Material.IRON_PICKAXE.name)
                                .put(ShiroiConstants.NAME_IDENTIFIER, "$2Clears the mine!")
                                .build()
                ),
                MineMeConfigKey.GUI,
                "resources.resetButton"
        )
    }


}