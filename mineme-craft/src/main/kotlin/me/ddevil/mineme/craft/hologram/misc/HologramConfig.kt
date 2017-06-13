package me.ddevil.mineme.craft.hologram.misc

import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.util.*

class HologramConfig(holoConfig: Map<String, Any>) {
    val useCustomHologramText = holoConfig.getBooleanOrNull(MineMeConstants.MINE_USE_CUSTOM_HOLOGRAM_TEXT_IDENTIFIER) ?: false
    val customHologramText = holoConfig.getListOrNull<String>(MineMeConstants.MINE_CUSTOM_HOLOGRAM_TEXT_IDENTIFIER) ?: emptyList()
    val hologramsEnabled = holoConfig.getBoolean(MineMeConstants.MINE_HOLOGRAM_ENABLED_IDENTIFIER)
    val holoUpdaterConfig = holoConfig.getMapAny(MineMeConstants.MINE_HOLOGRAM_UPDATER_IDENTIFIER)
    val hologramUpdaterName = holoUpdaterConfig.getString(MineMeConstants.MINE_CONFIG_OPTION_NAME_IDENTIFIER)
    val hologramUpdaterMeta = holoUpdaterConfig.getMapAnyOrNull(MineMeConstants.MINE_CONFIG_OPTION_META_IDENTIFIER)
    val formationConfig = holoConfig.getMapAnyOrNull(MineMeConstants.MINE_HOLOGRAM_FORMATION_IDENTIFIER)
    val formationName = formationConfig?.getStringOrNull(MineMeConstants.MINE_CONFIG_OPTION_NAME_IDENTIFIER)
    val formationMeta = formationConfig?.getMapAnyOrNull(MineMeConstants.MINE_CONFIG_OPTION_META_IDENTIFIER)
}
