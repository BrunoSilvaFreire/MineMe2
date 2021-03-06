package me.ddevil.mineme.api

import java.text.DecimalFormat

object MineMeConstants {
    val DECIMAL_FORMAT = DecimalFormat(",###.##")

    const val MINE_NAME_IDENTIFIER = "name"
    const val MINE_ALIAS_IDENTIFIER = "alias"
    const val MINE_RESET_DELAY_IDENTIFIER = "resetDelay"
    const val MINE_REGION_IDENTIFIER = "region"
    const val MINE_ICON_IDENTIFIER = "icon"
    const val MINE_REPOPULATOR_IDENTIFIER = "repopulator"
    const val MINE_COMPOSITION_IDENTIFIER = "composition"
    const val MINE_COMPOSITION_MATERIALS_IDENTIFIER = "materials"
    const val MINE_HOLOGRAM_FORMATION_IDENTIFIER = "formation"
    const val MINE_HOLOGRAM_IDENTIFIER = "holograms"
    const val MINE_HOLOGRAM_ENABLED_IDENTIFIER = "enabled"
    const val MATERIAL_TYPE_IDENTIFIER = "type"

    const val STORAGE_FILE_EXTENSION = "json"

    const val DEFAULT_FILE_EXTENSION = "yml"
    const val MINE_TYPE_IDENTIFIER = "type"
    const val MINE_ENABLED_IDENTIFIER = "enabled"
    const val MINE_WORLD_IDENTIFIER = "world"
    const val MINE_RESET_CONFIG_IDENTIFIER = "resetConfig"
    const val MINE_EXECUTOR_CONFIG_IDENTIFIER = "executor"
    const val MINE_EXECUTOR_TYPE_IDENTIFIER = "type"
    const val MINE_EXECUTOR_META_IDENTIFIER = "meta"
    const val MINE_MATERIAL_PERCENTAGE_IDENTIFIER = "percentage"

    const val MINE_USE_CUSTOM_HOLOGRAM_TEXT_IDENTIFIER = "useCustomHologramText"
    const val MINE_CUSTOM_HOLOGRAM_TEXT_IDENTIFIER = "customHologramText"

    const val AUTHOR_IDENTIFIER = "author"
    const val MINE_FOLDER_NAME = "mines"
    const val COMPOSITION_FOLDER_NAME = "compositions"

    const val API_BASE_URL = "http://localhost:8080/minemecentral/"
    const val REGISTERED_IDENTIFIER = "registered"
    const val ID_IDENTIFIER = "id"

    //Variables
    const val MINE_MINED_BLOCKS_IDENTIFIER = "minedBlocks"
    const val MINE_VOLUME_IDENTIFIER = "volume"
    const val MINE_BLOCKS_PERCENT_LEFT_IDENTIFIER = "mineBlocksPercent"
    const val MINE_RESET_TIME_PASSED_IDENTIFIER = "resetTimePassed"
    const val MINE_TOTAL_RESET_TIME_IDENTIFIER = "totalResetTime"
    const val MINE_RESET_TIME_LEFT_IDENTIFIER = "resetTimeLeft"
    const val MINE_HOLOGRAM_UPDATER_IDENTIFIER = "hologramUpdater"
    const val CENTER_KEY = "center"
    const val RADIUS_KEY = "radius"
    const val MAXIMUM_Y_KEY = "maxY"
    const val MINIMUM_Y_KEY = "minY"

    const val MINE_CONFIG_OPTION_NAME_IDENTIFIER = "name"
    const val MINE_CONFIG_OPTION_META_IDENTIFIER = "meta"
    const val HOLOGRAM_LINE_SIZE = 0.4
    val MINE_REQUIRED_FIELDS = arrayOf(
            MINE_ENABLED_IDENTIFIER,
            MINE_NAME_IDENTIFIER,
            MINE_ALIAS_IDENTIFIER,
            MINE_ICON_IDENTIFIER,
            MINE_WORLD_IDENTIFIER,
            MINE_TYPE_IDENTIFIER,
            MINE_COMPOSITION_IDENTIFIER,
            MINE_REGION_IDENTIFIER
    )
}