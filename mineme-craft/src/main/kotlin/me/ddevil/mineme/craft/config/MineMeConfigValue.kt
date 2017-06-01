package me.ddevil.mineme.craft.config

import com.google.common.collect.ImmutableMap
import me.ddevil.mineme.api.MineMeConstants.MATERIAL_TYPE_IDENTIFIER
import me.ddevil.mineme.craft.api.MineMeCraftConstants
import me.ddevil.mineme.craft.api.mine.executor.MineResetExecutorType
import me.ddevil.shiroi.craft.config.FileConfigValue
import me.ddevil.shiroi.craft.util.createConfig
import org.bukkit.Material

class MineMeConfigValue<out T : Any>(
        override val defaultValue: T,
        override val source: MineMeConfigSource,
        override val path: String
) : FileConfigValue<T, MineMeConfigSource> {
    companion object {

        val COLOR_DESIGN = MineMeConfigValue(
                createConfig(MineMeCraftConstants.MINE_ME_COLOR_DESIGN.serialize()),
                MineMeConfigSource.MAIN,
                "plugin.colorDesign")

        val PLUGIN_PREFIX = MineMeConfigValue(
                "$1&lMine$2&lMe",
                MineMeConfigSource.MAIN,
                "plugin.prefix")

        val MESSAGE_SEPARATOR = MineMeConfigValue(
                " &6&l> $3",
                MineMeConfigSource.MAIN,
                "plugin.messageSeparator")

        val DEFAULT_MINE_RESET_DELAY = MineMeConfigValue(
                10,
                MineMeConfigSource.MAIN,
                "mines.defaults.resetDelay"
        )
        val HOLOGRAM_PROVIDER = MineMeConfigValue(
                "HOLOGRAPHIC_DISPLAYS",
                MineMeConfigSource.MAIN,
                "plugin.holograms.provider"
        )
        val HOLOGRAM_ENABLED = MineMeConfigValue(
                false,
                MineMeConfigSource.MAIN,
                "plugin.holograms.enabled"
        )
        val COMMON_MATERIALS = MineMeConfigValue(
                listOf("STONE", "COBBLESTONE", "SANDSTONE", "NETHERRACK"),
                MineMeConfigSource.MAIN,
                "mines.composition.commonMaterials"
        )
        val DEFAULT_MINE_ICON = MineMeConfigValue(
                createConfig(
                        ImmutableMap.of(
                                MATERIAL_TYPE_IDENTIFIER, Material.REDSTONE_BLOCK.name
                        )
                ),
                MineMeConfigSource.MAIN,
                "mines.defaults.icon"
        )
        val  DEFAULT_MINE_RESET_EXECUTOR= MineMeConfigValue(
                MineResetExecutorType.SYNC.name,
                MineMeConfigSource.MAIN,
                "mines.defaults.reset.executor.type"
        )
    }
}