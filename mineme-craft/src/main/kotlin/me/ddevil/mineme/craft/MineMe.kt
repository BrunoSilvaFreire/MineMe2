package me.ddevil.mineme.craft

import com.sk89q.worldedit.bukkit.WorldEditPlugin
import me.ddevil.mineme.api.network.NetworkManager
import me.ddevil.mineme.craft.api.hologram.HologramManager
import me.ddevil.mineme.craft.api.mine.MineManager
import me.ddevil.mineme.craft.command.CreationCommand
import me.ddevil.mineme.craft.command.ListCommand
import me.ddevil.mineme.craft.command.UICommand
import me.ddevil.mineme.craft.config.MineMeConfigManager
import me.ddevil.mineme.craft.hologram.InternalHologramManager
import me.ddevil.mineme.craft.message.MineMeMessageManager
import me.ddevil.mineme.craft.mine.InternalMineManager
import me.ddevil.mineme.craft.network.InternalNetworkManager
import me.ddevil.mineme.craft.ui.MineMeUIManager
import me.ddevil.shiroi.craft.plugin.AbstractPlugin
import me.ddevil.shiroi.craft.plugin.PluginSettings
import kotlin.properties.Delegates

@PluginSettings(primaryAcronym = "mm", aliases = arrayOf("mrl", "mineresetlite"))
class MineMe : AbstractPlugin<MineMeMessageManager, MineMeConfigManager>() {

    val uiManager: MineMeUIManager = MineMeUIManager(this)
    var mineManager: MineManager by Delegates.notNull<MineManager>()
        private set
    var hologramManager: HologramManager by Delegates.notNull<HologramManager>()
        private set
    val networkManager: NetworkManager

    init {
        this.networkManager = InternalNetworkManager()
    }

    override fun reload() {
        super.reload()
        mineManager.reload()
    }

    override fun loadConfigManager(): MineMeConfigManager {
        return MineMeConfigManager(this)
    }

    override fun loadMessageManager(): MineMeMessageManager {
        return MineMeMessageManager(this)
    }

    var worldEdit: WorldEditPlugin by Delegates.notNull<WorldEditPlugin>()
        private set

    override fun onEnable0() {
        this.worldEdit = getPlugin(WorldEditPlugin::class.java) ?: throw IllegalStateException("Couldn't find WorldEdit in plugins!")
        commandManager.registerCommand(CreationCommand(this, worldEdit))
        commandManager.registerCommand(ListCommand(this))
        commandManager.registerCommand(UICommand(this))
        this.hologramManager = InternalHologramManager(this)
        this.mineManager = InternalMineManager(this)
        mineManager.enable()
        uiManager.enable()
    }



}