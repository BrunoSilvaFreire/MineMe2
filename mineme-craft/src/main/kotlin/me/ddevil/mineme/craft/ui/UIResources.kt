package me.ddevil.mineme.craft.ui

import com.google.common.collect.ImmutableMap
import me.ddevil.mineme.api.MineMeConstants.MATERIAL_TYPE_IDENTIFIER
import me.ddevil.mineme.craft.config.MineMeConfigSource
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.shiroi.craft.config.YAMLFileConfigManager
import me.ddevil.shiroi.craft.message.MessageManager
import me.ddevil.shiroi.craft.util.createConfig
import me.ddevil.shiroi.craft.util.parseConfig
import me.ddevil.shiroi.util.DEFAULT_SHIROI_ITEM_DATA_IDENTIFIER
import me.ddevil.shiroi.util.DEFAULT_SHIROI_ITEM_LORE_IDENTIFIER
import me.ddevil.util.DEFAULT_NAME_IDENTIFIER
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object UIResources {

    lateinit var PRIMARY_BACKGROUND: ItemStack
        private set
    lateinit var SECONDARY_BACKGROUND: ItemStack
        private set
    lateinit var TELEPORT_BUTTON: ItemStack
        private set
    lateinit var RESET_BUTTON: ItemStack
        private set
    lateinit var CLEAR_BUTTON: ItemStack
        private set

    fun loadItems(configManager: YAMLFileConfigManager<MineMeConfigSource>, messageManager: MessageManager) {
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
                                MATERIAL_TYPE_IDENTIFIER, Material.IRON_FENCE.name,
                                DEFAULT_NAME_IDENTIFIER, "&r"
                        )
                ),
                MineMeConfigSource.GUI,
                "resources.primaryBackground"
        )
        val SECONDARY_BACKGROUND = MineMeConfigValue(
                createConfig(
                        ImmutableMap.of(
                                MATERIAL_TYPE_IDENTIFIER, Material.STAINED_GLASS_PANE.name,
                                DEFAULT_SHIROI_ITEM_DATA_IDENTIFIER, 15,
                                DEFAULT_NAME_IDENTIFIER, "&r"
                        )
                ),
                MineMeConfigSource.GUI,
                "resources.secondaryBackground"
        )
        val TELEPORT_BUTTON = MineMeConfigValue(
                createConfig(
                        ImmutableMap.builder<String, Any>()
                                .put(MATERIAL_TYPE_IDENTIFIER, Material.ENDER_PEARL.name)
                                .put(DEFAULT_NAME_IDENTIFIER, "$2Teleport to mine location!")
                                .build()
                ),
                MineMeConfigSource.GUI,
                "resources.teleportButton"
        )
        val RESET_BUTTON = MineMeConfigValue(
                createConfig(
                        ImmutableMap.builder<String, Any>()
                                .put(MATERIAL_TYPE_IDENTIFIER, Material.IRON_PICKAXE.name)
                                .put(DEFAULT_NAME_IDENTIFIER, "$2Resets the mine!")
                                .put(DEFAULT_SHIROI_ITEM_LORE_IDENTIFIER, listOf(
                                        "$3Uses the default mine repopulator",
                                        "$3and executor to reset the mine."
                                ))
                                .build()
                ),
                MineMeConfigSource.GUI,
                "resources.resetButton"
        )

        val CLEAR_BUTTON = MineMeConfigValue(
                createConfig(
                        ImmutableMap.builder<String, Any>()
                                .put(MATERIAL_TYPE_IDENTIFIER, Material.IRON_PICKAXE.name)
                                .put(DEFAULT_NAME_IDENTIFIER, "$2Clears the mine!")
                                .build()
                ),
                MineMeConfigSource.GUI,
                "resources.resetButton"
        )
    }


}