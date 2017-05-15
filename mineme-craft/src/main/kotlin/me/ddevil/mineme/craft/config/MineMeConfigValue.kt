package me.ddevil.mineme.craft.config

import com.google.common.collect.ImmutableMap
import me.ddevil.mineme.craft.api.MineMeCraftConstants
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutorType
import me.ddevil.shiroi.craft.config.FileConfigValue
import me.ddevil.shiroi.craft.util.createConfig
import me.ddevil.shiroi.util.ShiroiConstants
import org.bukkit.Material

class MineMeConfigValue<T>(override val defaultValue: T, override val key: MineMeConfigKey, override val path: String) : FileConfigValue<T, MineMeConfigKey> {
    companion object {

        val COLOR_DESIGN = MineMeConfigValue(
                createConfig(MineMeCraftConstants.MINE_ME_COLOR_DESIGN.serialize()),
                MineMeConfigKey.MAIN,
                "plugin.colorDesign")

        val PLUGIN_PREFIX = MineMeConfigValue(
                "$1&lMine$2&lMe",
                MineMeConfigKey.MAIN,
                "plugin.prefix")

        val MESSAGE_SEPARATOR = MineMeConfigValue(
                " &6&l> $3",
                MineMeConfigKey.MAIN,
                "plugin.messageSeparator")

        val DEFAULT_MINE_RESET_DELAY = MineMeConfigValue(
                10,
                MineMeConfigKey.MAIN,
                "mines.defaults.resetDelay"
        )
        val HOLOGRAM_PROVIDER = MineMeConfigValue(
                "HOLOGRAPHIC_DISPLAYS",
                MineMeConfigKey.MAIN,
                "plugin.holograms.provider"
        )
        val HOLOGRAM_ENABLED = MineMeConfigValue(
                false,
                MineMeConfigKey.MAIN,
                "plugin.holograms.enabled"
        )
        val COMMON_MATERIALS = MineMeConfigValue(
                listOf("STONE", "COBBLESTONE", "SANDSTONE", "NETHERRACK"),
                MineMeConfigKey.MAIN,
                "mines.composition.commonMaterials"
        )
        val DEFAULT_MINE_ICON = MineMeConfigValue(
                createConfig(
                        ImmutableMap.of(
                                ShiroiConstants.MATERIAL_TYPE_IDENTIFIER, Material.REDSTONE_BLOCK.name
                        )
                ),
                MineMeConfigKey.MAIN,
                "mines.defaults.icon"
        )
        val  DEFAULT_MINE_RESET_EXECUTOR= MineMeConfigValue(
                MineResetExecutorType.SYNC.name,
                MineMeConfigKey.MAIN,
                "mines.defaults.reset.executor.type"
        )
    }
}