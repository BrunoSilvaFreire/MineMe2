package me.ddevil.mineme.craft

import com.sk89q.worldedit.bukkit.WorldEditPlugin
import me.ddevil.mineme.api.network.NetworkManager
import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.mine.MineManager
import me.ddevil.mineme.craft.command.CreationCommand
import me.ddevil.mineme.craft.command.GeneralCommands
import me.ddevil.mineme.craft.command.UICommand
import me.ddevil.mineme.craft.config.MineMeConfigSource
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.hologram.InternalHologramManager
import me.ddevil.mineme.craft.mine.InternalMineManager
import me.ddevil.mineme.craft.network.InternalNetworkManager
import me.ddevil.mineme.craft.ui.MineMeUIManager
import me.ddevil.shiroi.craft.config.YAMLFileConfigManager
import me.ddevil.shiroi.craft.message.lang.FileLangMessageManager
import me.ddevil.shiroi.craft.plugin.AbstractPlugin
import me.ddevil.shiroi.craft.plugin.PluginSettings
import org.bukkit.command.CommandSender

@PluginSettings(primaryAcronym = "mm", aliases = arrayOf("mrl", "mineresetlite", "mine"))
class MineMe : AbstractPlugin<
        FileLangMessageManager<MineMeConfigSource, MineMeConfigValue<String>>,
        YAMLFileConfigManager<MineMeConfigSource>>() {

    val uiManager: MineMeUIManager = MineMeUIManager(this)
    lateinit var mineManager: MineManager
        private set
    lateinit var hologramManager: HologramManager
        private set
    val networkManager: NetworkManager

    init {
        this.networkManager = InternalNetworkManager()
    }

    override fun reload(sender: CommandSender) {
        super.reload(sender)
        mineManager.reload(sender)
        uiManager.reload(sender)
    }

    override fun reload() {
        super.reload()
        mineManager.reload()
        uiManager.reload()
    }

    override fun loadConfigManager() = YAMLFileConfigManager(this, MineMeConfigValue.COLOR_DESIGN)

    override fun loadMessageManager() = FileLangMessageManager<MineMeConfigSource, MineMeConfigValue<String>>(
            this,
            MineMeConfigValue.MESSAGE_SEPARATOR,
            MineMeConfigValue.PLUGIN_PREFIX,
            configManager,
            emptyList()
    )

    lateinit var worldEdit: WorldEditPlugin
        private set

    override fun onEnable0() {
        this.worldEdit = getPlugin(WorldEditPlugin::class.java) ?: throw IllegalStateException("Couldn't find WorldEdit in plugins!")

        this.commandManager.registerCommand(CreationCommand(this, worldEdit))
        this.commandManager.registerCommand(GeneralCommands(this))
        this.commandManager.registerCommand(UICommand(this))

        this.hologramManager = InternalHologramManager(this)
        this.mineManager = InternalMineManager(this)
        mineManager.enable()
        uiManager.enable()
    }


}