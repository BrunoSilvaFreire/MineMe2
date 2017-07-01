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
                60,
                MineMeConfigSource.MAIN,
                "mines.defaults.reset.resetDelay"
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
        val DEFAULT_MINE_RESET_EXECUTOR = MineMeConfigValue(
                MineResetExecutorType.SYNC.name,
                MineMeConfigSource.MAIN,
                "mines.defaults.reset.executor.type"
        )
        val DEFAULT_MINE_HOLOGRAM_TEXT = MineMeConfigValue(
                listOf(
                        "$1&lMine$2&lMe $4&l&o2",
                        "$1{alias}",
                        "$1{minedBlocks}&8/&7{totalBlocks} ($1{mineBlocksPercent}&7)",
                        "$2{resetTimePassed}&8/&7{totalResetTime} ($2{resetTimeLeft}&7)",
                        "$1&lMine$2&lMe $4&l&o2"
                ),
                MineMeConfigSource.MAIN,
                "mines.holograms.defaultText"
        )
        val COMMAND_MINE_INFO = MineMeConfigValue(
                listOf(
                        "name: $1{name}",
                        "alias: $1{alias}",
                        "world: $1{world}",
                        "type: &d{type}",
                        "composition: $1{composition}",
                        "minedBlocks: $1{minedBlocks}",
                        "volume: $1{volume}",
                        "mineBlocksPercent: $1{mineBlocksPercent}",
                        "resetTimePassed: $1{resetTimePassed}",
                        "totalResetTime: $1{totalResetTime}",
                        "resetTimeLeft: $1{resetTimeLeft}"
                ),
                MineMeConfigSource.MESSAGES,
                "command.info"
        )
        val LOAD_EXAMPLES = MineMeConfigValue(
                true,
                MineMeConfigSource.MAIN,
                "misc.loadExamples"
        )
        val MATERIAL_ICON_NAME = MineMeConfigValue(
                "$1{type}$3:$2{data}",
                MineMeConfigSource.GUI,
                "misc.materialIcon.name"
        )
        val MATERIAL_ICON_LORE = MineMeConfigValue(
                listOf("$3Percentage: $1{percentage}$3%",
                        "{value}"),
                MineMeConfigSource.GUI,
                "misc.materialIcon.lore"
        )
    }
}