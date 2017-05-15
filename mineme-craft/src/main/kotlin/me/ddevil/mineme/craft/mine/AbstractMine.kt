package me.ddevil.mineme.craft.mine

import com.google.common.collect.ImmutableMap
import com.sk89q.worldedit.regions.Region
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.craft.api.MineMeCraftConstants
import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.mineme.craft.api.mine.MineRepopulator
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutor
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutorType
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.mineme.craft.exception.MineNotEnabledException
import me.ddevil.mineme.craft.mine.misc.CountdownRunnable
import me.ddevil.mineme.craft.mine.misc.WorldEditIterator
import me.ddevil.mineme.craft.mine.repopulator.EmptyRepopulator
import me.ddevil.mineme.craft.mine.repopulator.FillMineRepopulator
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.shiroi.util.getOrException
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.inventory.ItemStack

abstract class AbstractMine<R : Region> : Mine {


    final override var alias: String
    final override var name: String
    final override var icon: ItemStack
    final override var defaultRepopulator: MineRepopulator
    final override var resetDelay: Int
    final override var enabled: Boolean
    final override var currentCountdown: Int
    final override var composition: MineComposition
    final override val world: World

    override val center: Location
        get() {
            val rCenter = region.center
            return Location(world, rCenter.x, rCenter.y, rCenter.z, rCenter.toYaw(), rCenter.toPitch())
        }

    override val topCenter: Location
        get() {
            val rCenter = region.center
            return Location(world, rCenter.x, region.maximumPoint.y, rCenter.z, rCenter.toYaw(), rCenter.toPitch())
        }

    @JvmOverloads
    constructor(plugin: MineMe,
                alias: String,
                name: String,
                composition: MineComposition,
                region: R,
                icon: ItemStack = plugin.mineManager.defaultMineIcon,
                defaultRepopulator: MineRepopulator = plugin.mineManager.defaultRepopulator,
                resetDelay: Int = plugin.configManager.getValue(MineMeConfigValue.DEFAULT_MINE_RESET_DELAY)
    ) {
        this.plugin = plugin
        this.alias = alias
        this.name = name
        this.resetDelay = resetDelay
        this.currentCountdown = resetDelay
        this.region = region
        this.world = Bukkit.getWorld(region.world!!.name)
        this.composition = composition
        this.icon = icon
        this.defaultRepopulator = defaultRepopulator
        this.enabled = true
        this.countdownRunnable = CountdownRunnable(this)
        this.counting = false
        this.defaultExecutor = plugin.mineManager.createDefaultResetExecutor(this)
    }

    constructor(plugin: MineMe, map: Map<String, Any>) {
        this.plugin = plugin
        //Misc
        this.name = map.getOrException(MineMeConstants.MINE_NAME_IDENTIFIER)
        this.alias = map.getOrException(MineMeConstants.MINE_ALIAS_IDENTIFIER)
        this.icon = ItemStack.deserialize(map.getOrException(MineMeConstants.MINE_ICON_IDENTIFIER))

        //Position
        this.world = Bukkit.getWorld(map.getOrException<String>(MineMeConstants.MINE_WORLD_IDENTIFIER))
        this.region = loadRegion(map.getOrException(MineMeConstants.MINE_REGION_IDENTIFIER))
        //Composition
        val compositionName = map.getOrException<String>(MineMeConstants.MINE_COMPOSITION_IDENTIFIER)
        this.composition = plugin.mineManager.getComposition(compositionName) ?: throw IllegalStateException("Couldn't find required composition '$compositionName' for mine $name!")

        //Resets
        val resetMap = map.getOrException<Map<String, Any>>(MineMeConstants.MINE_RESET_CONFIG_IDENTIFIER)
        //Repopulator
        val repopulatorName = resetMap.getOrException<String>(MineMeConstants.MINE_REPOPULATOR_IDENTIFIER)
        this.defaultRepopulator = plugin.mineManager.getRepopulator(repopulatorName) ?: throw IllegalStateException("Couldn't find required repopulator '$repopulatorName' for mine $name!")
        //Executor
        val executorMap = resetMap.getOrException<Map<String, Any>>(MineMeConstants.MINE_EXECUTOR_CONFIG_IDENTIFIER)
        val executorTypeName = executorMap.getOrException<String>(MineMeConstants.MINE_EXECUTOR_TYPE_IDENTIFIER)
        val executorMetaMap = executorMap.getOrException<Map<String, Any>>(MineMeConstants.MINE_EXECUTOR_META_IDENTIFIER)
        this.defaultExecutor = MineResetExecutorType.getType(executorTypeName).create(this, executorMetaMap, plugin)

        //Cooldown
        this.resetDelay = resetMap.getOrException(MineMeConstants.MINE_RESET_DELAY_IDENTIFIER)
        this.currentCountdown = resetDelay
        this.enabled = true

        this.countdownRunnable = CountdownRunnable(this)
        this.counting = false


    }

    override val fullName: String
        get() = "$name($alias)"
    protected val region: R

    override val timeToNextReset: Int
        get() = resetDelay - currentCountdown

    final override var counting: Boolean
        set(value) {
            if (field != value) {
                field = value
                if (counting) {
                    checkIfEnabled()
                    countdownRunnable = CountdownRunnable(this)
                    countdownRunnable.runTaskTimer(plugin,
                            MineMeCraftConstants.SECOND_TICK,
                            MineMeCraftConstants.SECOND_TICK)
                } else {
                    countdownRunnable.cancel()
                }
            }
        }

    private var countdownRunnable: CountdownRunnable

    @Transient
    var plugin: MineMe

    @Transient final
    override var defaultExecutor: MineResetExecutor


    override fun serialize(): Map<String, Any> = ImmutableMap.builder<String, Any>()
            .put(MineMeConstants.MINE_NAME_IDENTIFIER, name)
            .put(MineMeConstants.MINE_ALIAS_IDENTIFIER, alias)
            .put(MineMeConstants.MINE_ENABLED_IDENTIFIER, enabled)
            .put(MineMeConstants.MINE_RESET_CONFIG_IDENTIFIER, serializeResetConfig())
            .put(MineMeConstants.MINE_ICON_IDENTIFIER, icon.serialize())
            .put(MineMeConstants.MINE_TYPE_IDENTIFIER, type.name)
            .put(MineMeConstants.MINE_WORLD_IDENTIFIER, world.name)
            .put(MineMeConstants.MINE_COMPOSITION_IDENTIFIER, composition.name)
            .put(MineMeConstants.MINE_REGION_IDENTIFIER, serializeRegion()).build()

    private fun serializeResetConfig() = ImmutableMap.builder<String, Any>()
            .put(MineMeConstants.MINE_RESET_DELAY_IDENTIFIER, resetDelay)
            .put(MineMeConstants.MINE_REPOPULATOR_IDENTIFIER, defaultRepopulator.name)
            .put(MineMeConstants.MINE_EXECUTOR_CONFIG_IDENTIFIER, defaultExecutor.serialize())
            .build()

    abstract fun loadRegion(map: Map<String, Any>): R

    override fun fill(item: ItemStack) {
        checkIfEnabled()
        val repopulator = FillMineRepopulator(item)
        replaceBlocks(repopulator)
    }

    override fun reset() = reset(defaultRepopulator)


    override fun reset(repopulator: MineRepopulator) {
        reset(repopulator, defaultExecutor)
    }

    override fun reset(repopulator: MineRepopulator, executor: MineResetExecutor) {
        checkIfEnabled()
        replaceBlocks(repopulator, executor)
    }

    override fun reset(executor: MineResetExecutor) {
        reset(defaultRepopulator, executor)
    }

    override fun clear() {
        checkIfEnabled()
        replaceBlocks(EmptyRepopulator.INSTANCE)
    }


    private fun checkIfEnabled() {
        if (!enabled) {
            throw MineNotEnabledException(this)
        }
    }


    private fun replaceBlocks(repopulator: MineRepopulator, executor: MineResetExecutor = defaultExecutor) {
        executor.reset(repopulator)
    }

    abstract fun serializeRegion(): Map<String, Any>

    override fun iterator() = WorldEditIterator(world, region)

    override fun delete() {
        plugin.mineManager.delete(this)
    }

    override fun disable() {
        if (counting) {
            counting = false
        }
        if (enabled) {
            enabled = false
        }
    }

    override fun enable() {
        if (!enabled) {
            enabled = true
        }
        if (!counting) {
            counting = true
        }
    }
}

