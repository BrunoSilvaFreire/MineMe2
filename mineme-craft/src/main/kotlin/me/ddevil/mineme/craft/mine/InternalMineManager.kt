package me.ddevil.mineme.craft.mine

import co.aikar.taskchain.TaskChainTasks
import com.sk89q.worldedit.regions.Region
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.MineMeCraftConstants
import me.ddevil.mineme.craft.api.mine.*
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutor
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutorType
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.mine.loader.CuboidLoader
import me.ddevil.mineme.craft.mine.repopulator.EmptyRepopulator
import me.ddevil.mineme.craft.mine.repopulator.NormalRepopulator
import me.ddevil.shiroi.craft.log.DebugLevel
import me.ddevil.shiroi.craft.util.getOrException
import me.ddevil.shiroi.craft.util.parseConfig
import me.ddevil.shiroi.craft.util.set
import me.ddevil.shiroi.craft.util.toMap
import me.ddevil.util.Serializable
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack
import java.io.File
import java.util.*
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

    override fun reload() {
        for (mine in loadedMines) {
            mine.disable()
        }
        loadedCompositions.clear()
        loadedMines.clear()
        loadCompositionsAndMines()
    }

    override fun enable() {
        this.defaultMineIcon = parseConfig(plugin.configManager.getValue(MineMeConfigValue.DEFAULT_MINE_ICON), plugin.messageManager)
        loadCompositionsAndMines()
    }

    override fun disable() {
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

    override fun get(name: String) = loadedMines.firstOrNull { it.name == name }

    override fun getComposition(name: String) = loadedCompositions.firstOrNull { it.name == name }

    override fun getRepopulator(name: String) = registeredRepopulators.firstOrNull { it.name == name }

    override fun <R : Region> getLoader(type: Class<R>): MineLoader<R>? {
        return loaders
                .firstOrNull { it.regionClass == type }
                ?.let { it as? MineLoader<R> }
    }

    override fun getLoader(type: MineType): MineLoader<*>? {
        return loaders.firstOrNull { it.supportedType == type }
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
        plugin.pluginLogger.log("Loading mines...")
        for (file in minesFolder.listFiles()) {
            if (file.extension == MineMeConstants.DEFAULT_FILE_EXTENSION) {
                try {
                    val config = YamlConfiguration.loadConfiguration(file)
                    if (config.getBoolean(MineMeConstants.MINE_ENABLED_IDENTIFIER)) {
                        val mine = loadMine(config)
                        loadedMines.add(mine)
                        plugin.pluginLogger.log("Mine ${mine.name} loaded.")
                        mine.enable()
                    } else {
                        plugin.pluginLogger.log("Mine file ${file.name} is disabled, skipping...")

                    }
                } catch (e: Exception) {
                    plugin.pluginLogger.printException("There was a problem while loading mine file '${file.path}'! Skipping...", e)
                    continue
                }
            } else {
                plugin.pluginLogger.log("Found invalid file (${file.path}) in mines folder, please remove...", DebugLevel.OKAY_SOME_REAL_SHIT_HAPPENED)
            }
        }
        plugin.pluginLogger.log("Found a total of ${loadedMines.size} mines...")
        return loadedMines
    }

    private fun loadCompositionsFromFolder(): Set<MineComposition> {
        plugin.pluginLogger.log("Loading compositions...")
        val compositions = mutableSetOf<MineComposition>()
        for (file in compositionsFolder.listFiles()) {
            if (file.extension == MineMeCraftConstants.DEFAULT_FILE_EXTENSION) {
                val config = YamlConfiguration.loadConfiguration(file)
                val mine = MineComposition(config.toMap())
                compositions.add(mine)
                plugin.pluginLogger.log("Composition ${mine.name} loaded.")
            } else {
                plugin.pluginLogger.log("Found invalid file (${file.path}) in composition folder, please remove...", DebugLevel.OKAY_SOME_REAL_SHIT_HAPPENED)
            }
        }
        plugin.pluginLogger.log("Found a total of ${compositions.size} compositions...")
        return compositions
    }

    private fun loadMine(config: YamlConfiguration): Mine {
        val type = MineType.valueOf(config.getOrException(MineMeConstants.MINE_TYPE_IDENTIFIER))
        val loader = getLoader(type) ?: throw IllegalStateException("Unsupported mine type '${type.name}'!")
        return loader.loadMine(config.toMap())
    }

    private fun getMineCompositionFile(mineComposition: MineComposition) = File(compositionsFolder, "${mineComposition.name}.${MineMeCraftConstants.DEFAULT_FILE_EXTENSION}")

    private fun getMineFile(mine: Mine) = File(minesFolder, "${mine.name}.${MineMeCraftConstants.DEFAULT_FILE_EXTENSION}")


    override fun save(mine: Mine) {
        plugin.pluginLogger.log("Saving mine '${mine.name}' ...")
        saveSerializable(mine, getMineFile(mine))
    }

    override fun save(mineComposition: MineComposition) {
        plugin.pluginLogger.log("Saving composition '${mineComposition.name}'...")
        saveSerializable(mineComposition, getMineCompositionFile(mineComposition))
    }

    private fun saveSerializable(serializable: Serializable, mineFile: File) {
        plugin.chainFactory.newChain<Any>().asyncFirst<Any>(
                TaskChainTasks.FirstTask<Any> {
                    val config = YamlConfiguration()
                    config.set(serializable.serialize())
                    config.save(mineFile)
                    return@FirstTask null

                }).execute()
    }

    override fun delete(mine: Mine) {
        plugin.pluginLogger.log("Deleting mine ${mine.name} ...")
        mine.disable()
        if (loadedMines.contains(mine)) {
            loadedMines.remove(mine)
        }
        getMineFile(mine).delete()
    }
}