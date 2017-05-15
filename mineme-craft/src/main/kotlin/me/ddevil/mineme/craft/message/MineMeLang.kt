package me.ddevil.mineme.craft.message

import me.ddevil.mineme.craft.config.MineMeConfigKey
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.shiroi.craft.message.lang.Lang

object MineMeLang {
    val COMMAND_MINE_CREATE_NAME_REQUIRED = Lang(Keys.COMMAND_MINE_CREATE_NAME_REQUIRED)

    val COMMAND_MINE_CREATE_ALIAS_REQUIRED = Lang(Keys.COMMAND_MINE_CREATE_ALIAS_REQUIRED)

    val COMMAND_MINE_CREATE_SELECTION_UNSUPPORTED = Lang(Keys.COMMAND_MINE_CREATE_SELECTION_UNSUPPORTED)

    val COMMAND_MINE_CREATE_SUCCESSFUL = Lang(Keys.COMMAND_MINE_CREATE_SUCCESSFUL)

    val COMPOSITION_NOT_FOUND = Lang(Keys.COMPOSITION_NOT_FOUND)

    val COMMAND_MINE_CREATE_COMPOSITION_REQUIRED = Lang(Keys.COMMAND_MINE_CREATE_COMPOSITION_REQUIRED)

    val COMMAND_MINE_CREATE_WORLD_EDIT_SELECTION_REQUIRED = Lang(Keys.WORLD_EDIT_SELECTION_REQUIRED)

    val COMMAND_MINE_CREATE_WORLD_EDIT_SELECTION_NOT_COMPLETE = Lang(Keys.WORLD_EDIT_SELECTION_NOT_COMPLETE)

    val COMMAND_COMPOSITION_CREATE_ALIAS_REQUIRED = Lang(Keys.COMMAND_COMPOSITION_CREATE_ALIAS_REQUIRED)

    val COMMAND_COMPOSITION_CREATE_SUCCESSFUL = Lang(Keys.COMMAND_COMPOSITION_CREATE_SUCCESSFUL)

    val COMMAND_COMPOSITION_CREATE_NAME_REQUIRED = Lang(Keys.COMMAND_COMPOSITION_CREATE_NAME_REQUIRED)

    val COMMAND_MINE_LIST_HEADER = Lang(Keys.COMMAND_MINE_LIST_HEADER)

    val COMMAND_MINE_LIST_FOUND = Lang(Keys.COMMAND_MINE_LIST_FOUND)

    val COMMAND_MINE_LIST_EMPTY = Lang(Keys.COMMAND_MINE_LIST_EMPTY)

    val COMMAND_COMPOSITION_LIST_HEADER = Lang(Keys.COMMAND_COMPOSITION_LIST_HEADER)

    val COMMAND_COMPOSITION_LIST_FOUND = Lang(Keys.COMMAND_COMPOSITION_LIST_FOUND)

    val COMMAND_COMPOSITION_LIST_EMPTY = Lang(Keys.COMMAND_COMPOSITION_LIST_EMPTY)

    object Keys {

        val WORLD_EDIT_SELECTION_REQUIRED = MineMeConfigValue(
                "$4You need to have a world edit selection!",
                MineMeConfigKey.MESSAGES,
                "command.creation.mine.worldEdit.selectionRequired"
        )
        val WORLD_EDIT_SELECTION_NOT_COMPLETE = MineMeConfigValue(
                "$4Your world edit needs to be complete!",
                MineMeConfigKey.MESSAGES,
                "command.creation.mine.worldEdit.selectionIncomplete"
        )

        val COMMAND_MINE_CREATE_NAME_REQUIRED = MineMeConfigValue(
                "$4You need to provide a name for the mine!",
                MineMeConfigKey.MESSAGES,
                "command.creation.mine.nameRequired"
        )
        val COMMAND_MINE_CREATE_COMPOSITION_REQUIRED = MineMeConfigValue(
                "$4You need to provide a composition for the mine!",
                MineMeConfigKey.MESSAGES,
                "command.creation.mine.compositionRequired"
        )
        val COMMAND_MINE_CREATE_ALIAS_REQUIRED = MineMeConfigValue(
                "$4You need to provide an alias for the mine!",
                MineMeConfigKey.MESSAGES,
                "command.creation.mine.aliasRequired"
        )

        val COMMAND_MINE_CREATE_SELECTION_UNSUPPORTED = MineMeConfigValue(
                "$4MineMe does not currently support this selection type :c ($1{type}$4)",
                MineMeConfigKey.MESSAGES,
                "command.creation.mine.selectionUnsupported"
        )
        val COMMAND_MINE_CREATE_SUCCESSFUL = MineMeConfigValue(
                "$3Mine $5{name} $3successfully created! (&d{type}$3)",
                MineMeConfigKey.MESSAGES,
                "command.creation.mine.success"
        )
        val COMPOSITION_NOT_FOUND = MineMeConfigValue(
                "$4Composition $1{name} $4not found!",
                MineMeConfigKey.MESSAGES,
                "mine.composition.notFound"
        )
        val COMMAND_COMPOSITION_CREATE_NAME_REQUIRED = MineMeConfigValue(
                "$4You need to provide a name for the composition!",
                MineMeConfigKey.MESSAGES,
                "command.creation.composition.nameRequired"
        )
        val COMMAND_COMPOSITION_CREATE_ALIAS_REQUIRED = MineMeConfigValue(
                "$4You need to provide an alias for the composition!",
                MineMeConfigKey.MESSAGES,
                "command.creation.composition.aliasRequired"
        )
        val COMMAND_COMPOSITION_CREATE_SUCCESSFUL = MineMeConfigValue(
                "$3Composition $5{name} $3successfully created!",
                MineMeConfigKey.MESSAGES,
                "command.creation.composition.success"
        )
        val COMMAND_MINE_LIST_HEADER = MineMeConfigValue(
                "$3Found mines:",
                MineMeConfigKey.MESSAGES,
                "command.list.mine.header"
        )
        val COMMAND_MINE_LIST_FOUND = MineMeConfigValue(
                "$1{name} $3($2{alias}$3, &d{type}) $3@ $1{world}",
                MineMeConfigKey.MESSAGES,
                "command.list.mine.found"
        )
        val  COMMAND_MINE_LIST_EMPTY = MineMeConfigValue(
                "$4There are no mines loaded!",
                MineMeConfigKey.MESSAGES,
                "command.list.mine.empty"
        )
        val COMMAND_COMPOSITION_LIST_HEADER = MineMeConfigValue(
                "$3Found compositions:",
                MineMeConfigKey.MESSAGES,
                "command.list.compositions.header"
        )
        val COMMAND_COMPOSITION_LIST_FOUND = MineMeConfigValue(
                "$1{name} $3($2{alias}$3, isEmpty=&d{empty}$3)",
                MineMeConfigKey.MESSAGES,
                "command.list.compositions.found"
        )
        val  COMMAND_COMPOSITION_LIST_EMPTY = MineMeConfigValue(
                "$4There are no mines compositions!",
                MineMeConfigKey.MESSAGES,
                "command.list.compositions.empty"
        )
    }


}