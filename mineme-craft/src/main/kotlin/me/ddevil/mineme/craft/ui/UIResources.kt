package me.ddevil.mineme.craft.ui

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
    lateinit var BACK_BUTTON: ItemStack
        private set
    lateinit var CLOSE_BUTTON: ItemStack
        private set
    lateinit var DISABLE_BUTTON: ItemStack
        private set
    lateinit var DELETE_BUTTON: ItemStack
        private set
    lateinit var PAUSE_COUNTDOWN_BUTTON: ItemStack
        private set
    lateinit var RESUME_COUNTDOWN_BUTTON: ItemStack
        private set
    lateinit var CHANGE_COMPOSITION_BUTTON: ItemStack
        private set
    lateinit var INVALID_CONFIG_ICON: ItemStack
        private set

    fun loadItems(configManager: YAMLFileConfigManager<MineMeConfigSource>, messageManager: MessageManager) {
        this.PRIMARY_BACKGROUND = parseConfig(configManager.getValue(Keys.PRIMARY_BACKGROUND), messageManager)
        this.SECONDARY_BACKGROUND = parseConfig(configManager.getValue(Keys.SECONDARY_BACKGROUND), messageManager)
        this.TELEPORT_BUTTON = parseConfig(configManager.getValue(Keys.TELEPORT_BUTTON), messageManager)
        this.RESET_BUTTON = parseConfig(configManager.getValue(Keys.RESET_BUTTON), messageManager)
        this.CLEAR_BUTTON = parseConfig(configManager.getValue(Keys.CLEAR_BUTTON), messageManager)
        this.BACK_BUTTON = parseConfig(configManager.getValue(Keys.BACK_BUTTON), messageManager)
        this.CLOSE_BUTTON = parseConfig(configManager.getValue(Keys.CLOSE_BUTTON), messageManager)
        this.DISABLE_BUTTON = parseConfig(configManager.getValue(Keys.DISABLE_BUTTON), messageManager)
        this.DELETE_BUTTON = parseConfig(configManager.getValue(Keys.DELETE_BUTTON), messageManager)
        this.PAUSE_COUNTDOWN_BUTTON = parseConfig(configManager.getValue(Keys.PAUSE_COUNTDOWN_BUTTON), messageManager)
        this.RESUME_COUNTDOWN_BUTTON = parseConfig(configManager.getValue(Keys.RESUME_COUNTDOWN_BUTTON), messageManager)
        this.CHANGE_COMPOSITION_BUTTON = parseConfig(configManager.getValue(Keys.CHANGE_COMPOSITION_BUTTON),
                messageManager)
        this.INVALID_CONFIG_ICON = parseConfig(configManager.getValue(Keys.INVALID_CONFIG_ICON), messageManager)
    }

    object Keys {
        val PRIMARY_BACKGROUND = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.IRON_FENCE.name
                    this[DEFAULT_NAME_IDENTIFIER] = "&r"
                },
                MineMeConfigSource.GUI,
                "resources.primaryBackground"
        )
        val SECONDARY_BACKGROUND = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.STAINED_GLASS_PANE.name
                    this[DEFAULT_SHIROI_ITEM_DATA_IDENTIFIER] = 15
                    this[DEFAULT_NAME_IDENTIFIER] = "&r"
                }, MineMeConfigSource.GUI,
                "resources.secondaryBackground"
        )
        val TELEPORT_BUTTON = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.ENDER_PEARL.name
                    this[DEFAULT_NAME_IDENTIFIER] = "$2Teleport to validMine location!"
                },
                MineMeConfigSource.GUI,
                "resources.teleportButton"
        )
        val RESET_BUTTON = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.IRON_PICKAXE.name
                    this[DEFAULT_NAME_IDENTIFIER] = "$2Resets the validMine!"
                    this[DEFAULT_SHIROI_ITEM_LORE_IDENTIFIER] = listOf(
                            "$3Uses the default validMine repopulator",
                            "$3and executor to reset the validMine."
                    )
                },
                MineMeConfigSource.GUI,
                "resources.resetButton"
        )

        val CLEAR_BUTTON = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.IRON_PICKAXE.name
                    this[DEFAULT_NAME_IDENTIFIER] = "$2Clears the validMine!"
                },
                MineMeConfigSource.GUI,
                "resources.clearButton"
        )
        val BACK_BUTTON = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.EMERALD.name
                    this[DEFAULT_NAME_IDENTIFIER] = "$5Back"
                },
                MineMeConfigSource.GUI,
                "resources.backButton"
        )
        val CLOSE_BUTTON = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.BARRIER.name
                    this[DEFAULT_NAME_IDENTIFIER] = "$4Close"
                },
                MineMeConfigSource.GUI,
                "resources.closeButton"
        )
        val DISABLE_BUTTON = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.REDSTONE_TORCH_ON.name
                    this[DEFAULT_NAME_IDENTIFIER] = "$4Disable Mine"
                    this[DEFAULT_SHIROI_ITEM_LORE_IDENTIFIER] = listOf(
                            "$4This unloads the validMine and prevents ",
                            "$4it from being loaded in the next start-ups.",
                            "$4The validMine can be re-enabled through it's config"
                    )
                },
                MineMeConfigSource.GUI,
                "resources.disableButton"
        )
        val DELETE_BUTTON = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.TNT.name
                    this[DEFAULT_NAME_IDENTIFIER] = "$4Delete Mine"
                    this[DEFAULT_SHIROI_ITEM_LORE_IDENTIFIER] = listOf(
                            "$4This deletes the validMine permanently.",
                            "$4This is not reversible."
                    )
                },
                MineMeConfigSource.GUI,
                "resources.deleteButton"
        )
        val PAUSE_COUNTDOWN_BUTTON = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.INK_SACK.name
                    this[DEFAULT_SHIROI_ITEM_DATA_IDENTIFIER] = 1
                    this[DEFAULT_NAME_IDENTIFIER] = "$4Pause Mine Countdown"
                    this[DEFAULT_SHIROI_ITEM_LORE_IDENTIFIER] = listOf(
                            "$3This pauses the validMine's reset countdown.",
                            "$3The countdown can be resumed again by",
                            "$3pressing this button again."
                    )
                },
                MineMeConfigSource.GUI,
                "resources.pauseCountdown"
        )
        val RESUME_COUNTDOWN_BUTTON = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.INK_SACK.name
                    this[DEFAULT_SHIROI_ITEM_DATA_IDENTIFIER] = 10
                    this[DEFAULT_NAME_IDENTIFIER] = "$5Resume Mine Countdown"
                    this[DEFAULT_SHIROI_ITEM_LORE_IDENTIFIER] = listOf(
                            "$3This resumes the validMine's reset countdown.",
                            "$3The countdown can be again by",
                            "$3pressing this button again."
                    )
                },
                MineMeConfigSource.GUI,
                "resources.resumeCountdown"
        )
        val CHANGE_COMPOSITION_BUTTON = MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.DIAMOND_BLOCK.name
                    this[DEFAULT_NAME_IDENTIFIER] = "$1Change Composition"
                    this[DEFAULT_SHIROI_ITEM_LORE_IDENTIFIER] = listOf(
                            "$3Opens a menu to change this validMine's composition"
                    )
                },
                MineMeConfigSource.GUI,
                "resources.changeComposition"
        )
        val  INVALID_CONFIG_ICON= MineMeConfigValue(
                createConfig {
                    this[MATERIAL_TYPE_IDENTIFIER] = Material.BARRIER.name
                    this[DEFAULT_NAME_IDENTIFIER] = "$4Invalid config"
                },
                MineMeConfigSource.GUI,
                "resources.invalidConfig"
        )
    }


}