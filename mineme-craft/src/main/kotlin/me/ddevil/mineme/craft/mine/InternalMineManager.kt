package me.ddevil.mineme.craft.mine

import co.aikar.taskchain.TaskChainTasks
import com.sk89q.worldedit.regions.Region
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.mine.*
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutor
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutorType
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.event.MineDisabledEvent
import me.ddevil.mineme.craft.mine.config.InvalidMineConfig
import me.ddevil.mineme.craft.mine.config.MineConfig
import me.ddevil.mineme.craft.mine.config.ValidMineConfig
import me.ddevil.mineme.craft.mine.loader.CuboidLoader
import me.ddevil.mineme.craft.mine.repopulator.EmptyRepopulator
import me.ddevil.mineme.craft.mine.repopulator.NormalRepopulator
import me.ddevil.shiroi.craft.log.DebugLevel
import me.ddevil.shiroi.craft.util.parseConfig
import me.ddevil.shiroi.craft.util.set
import me.ddevil.shiroi.craft.util.toMap
import me.ddevil.util.Serializable
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack
import java.io.File
import java.io.FileFilter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class InternalMineManager(val plugin: MineMe) : MineManager {


    override fun createDefaultResetExecutor(arg: Mine): MineResetExecutor {
        return defaultExecutor.create(arg, emptyMap(), plugin)
    }

    /**
     * Mines and compositions are grouped together since they are "unique" and loadable
     */
    private var loadedCompositions: MutableSet<MineComposition> = mutableSetOf()
    private val loadedMines: MutableSet<Mine> = mutableSetOf()

    /**
     * Repopulators and loaders are grouped together since they are NOT "unique".
     */
    private val registeredRepopulators: MutableSet<MineRepopulator>
    private val registeredLoaders: MutableSet<MineLoader<*>>
    private var defaultExecutor: MineResetExecutorType
    val minesFolder: File
    val compositionsFolder: File

    init {
        registeredRepopulators = mutableSetOf(*createDefaultRepopulators())
        registeredLoaders = mutableSetOf(*createDefaultLoaders())
        this.minesFolder = File(plugin.dataFolder, MineMeConstants.MINE_FOLDER_NAME)
        this.compositionsFolder = File(plugin.dataFolder, MineMeConstants.COMPOSITION_FOLDER_NAME)
        this.defaultExecutor = MineResetExecutorType.getType(plugin.configManager.getValue(MineMeConfigValue.DEFAULT_MINE_RESET_EXECUTOR))
    }


    override val defaultRepopulator: MineRepopulator
        get() = NormalRepopulator.INSTANCE

    override val mines: Set<Mine>
        get() = HashSet(loadedMines)

    override val compositions: Set<MineComposition>
        get() = HashSet(loadedCompositions)

    override val loaders: Set<MineLoader<*>>
        get() = HashSet(registeredLoaders)

    override var defaultMineIcon: ItemStack by Delegates.notNull<ItemStack>()
        private set


    override fun getMine(name: String) = loadedMines.firstOrNull { it.name == name }

    override fun getComposition(name: String) = loadedCompositions.firstOrNull { it.name == name }

    override fun getRepopulator(name: String) = registeredRepopulators.firstOrNull { it.name == name }

    override fun <R : Region> getLoader(type: Class<R>): MineLoader<R>? {
        return loaders
                .firstOrNull { it.regionClass == type }
                ?.let { it as? MineLoader<R> }
    }

    override fun getLoader(type: MineType): MineLoader<*> {
        return loaders.first { it.supportedType == type }
    }

    override fun reload() {
        for (mine in loadedMines) {
            mine.disable()
        }
        loadedCompositions.clear()
        loadedMines.clear()
        loadCompositionsAndMines()
    }

    override fun reload(sender: CommandSender) {
        reload()
        plugin.messageManager.sendMessage(sender,
                "Loaded $1${loadedMines.size} $3mines and $1${compositions.size} $3compositions."
        )

    }

    override fun enable() {
        this.defaultMineIcon = parseConfig(plugin.configManager.getValue(MineMeConfigValue.DEFAULT_MINE_ICON),
                plugin.messageManager)
        loadCompositionsAndMines()
    }

    override fun disable() {
    }

    override fun disable(mine: Mine) {
        plugin.pluginLogger.log("Disabling mine ${mine.name} ...")
        loadedMines.remove(mine)
        println(loadedMines)
        println(mines)
        save(mine)
        MineDisabledEvent(mine).call()
    }

    override fun loadMine(validMineConfig: ValidMineConfig): Mine {
        if (hasMine(validMineConfig.name)) {
            throw IllegalArgumentException("The provided config already has a equivalent mine loaded!")
        }
        val mine = getLoader(validMineConfig.type).loadMine(validMineConfig.map)
        registerMine(mine)
        mine.counting = true
        return mine
    }

    override fun hasMine(name: String) = getMine(name) != null

    override fun hasComposition(name: String) = getComposition(name) != null

    override fun save(mine: Mine) {
        plugin.pluginLogger.log("Saving mine '${mine.name}' ...")
        saveSerializable(mine, getMineFile(mine))
    }

    override fun save(mineComposition: MineComposition) {
        plugin.pluginLogger.log("Saving composition '${mineComposition.name}'...")
        saveSerializable(mineComposition, getMineCompositionFile(mineComposition))
    }

    override fun delete(mine: Mine) {
        plugin.pluginLogger.log("Deleting mine ${mine.name} ...")
        mine.disable()
        loadedMines.remove(mine)
        println(loadedMines)
        println(mines)
        getMineFile(mine).delete()
    }

    override fun findUnloadedMines(): List<MineConfig> {
        val list = ArrayList<MineConfig>()
        val foundFiles = minesFolder.listFiles(FileFilter {
            return@FileFilter it.extension == MineMeConstants.DEFAULT_FILE_EXTENSION
        })
        for (file in foundFiles) {
            try {
                val config = ValidMineConfig(file)
                if (hasMine(config.name)) {
                    continue
                }
                list += config
            } catch (exception: Exception) {
                list += InvalidMineConfig(file)
            }
        }
        return list
    }

    private fun createDefaultRepopulators() = arrayOf(
            NormalRepopulator.INSTANCE,
            EmptyRepopulator.INSTANCE
    )

    private fun createDefaultLoaders(): Array<MineLoader<*>> {
        return arrayOf(
                CuboidLoader(plugin)
        )
    }

    override fun registerMine(mine: Mine) {
        loadedMines.add(mine)
        if (!getMineFile(mine).exists()) {
            save(mine)
        }
    }


    override fun registerMineComposition(composition: MineComposition) {
        if (!getMineCompositionFile(composition).exists()) {
            save(composition)
        }
        loadedCompositions.add(composition)
    }


    private fun getMineCompositionFile(comp: MineComposition) = File(compositionsFolder,
            "${comp.name}.${MineMeConstants.DEFAULT_FILE_EXTENSION}")

    private fun getMineFile(mine: Mine) = File(minesFolder, "${mine.name}.${MineMeConstants.DEFAULT_FILE_EXTENSION}")

    private fun saveSerializable(serializable: Serializable, file: File) {
        plugin.chainFactory.newChain<Any>().asyncFirst<Any>(
                TaskChainTasks.FirstTask<Any> {
                    val config = YamlConfiguration()
                    config.set(serializable.serialize())
                    config.save(file)
                    return@FirstTask null

                }).execute()
    }

    private fun loadCompositionsAndMines() {
        if (!minesFolder.exists()) {
            minesFolder.mkdirs()
        }
        if (!compositionsFolder.exists()) {
            compositionsFolder.mkdirs()
        }
        loadedCompositions.addAll(loadCompositionsFromFolder())
        loadedCompositions.forEach { registerMineComposition(it) }
        loadedMines.addAll(loadMinesFromFolder())
        loadedMines.forEach { registerMine(it) }
    }

    private fun loadMinesFromFolder(): Set<Mine> {
        val loadedMines = mutableSetOf<Mine>()
        for (mineConfig in findUnloadedMines().filterIsInstance<ValidMineConfig>()) {
            if (mineConfig.enabled) {
                val mine = loadMine(mineConfig)
                loadedMines.add(mine)
                plugin.pluginLogger.log("Mine ${mine.name} loaded.")
                mine.enable()
            } else {
                plugin.pluginLogger.log("Mine file ${mineConfig.file.name} is disabled, skipping...")

            }
        }
        plugin.pluginLogger.log("Found a total of ${loadedMines.size} mines...")
        return loadedMines
    }

    private fun loadCompositionsFromFolder(): Set<MineComposition> {
        plugin.pluginLogger.log("Loading compositions...")
        val compositions = mutableSetOf<MineComposition>()
        for (file in compositionsFolder.listFiles()) {
            if (file.extension == MineMeConstants.DEFAULT_FILE_EXTENSION) {
                val config = YamlConfiguration.loadConfiguration(file)
                val mine = MineComposition(config.toMap())
                compositions.add(mine)
                plugin.pluginLogger.log("Composition ${mine.name} loaded.")
            } else {
                plugin.pluginLogger.log("Found invalid file (${file.path}) in composition folder, please remove...",
                        DebugLevel.OKAY_SOME_REAL_SHIT_HAPPENED)
            }
        }
        plugin.pluginLogger.log("Found a total of ${compositions.size} compositions...")
        return compositions
    }

}