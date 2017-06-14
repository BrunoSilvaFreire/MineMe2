package me.ddevil.mineme.craft.message

import me.ddevil.mineme.craft.config.MineMeConfigSource
import me.ddevil.mineme.craft.config.MineMeConfigValue
import me.ddevil.shiroi.craft.message.lang.Lang

object MineMeLang {
    val MINE_NOT_FOUND = Lang(Keys.MINE_NOT_FOUND)

    val COMMAND_MINE_CREATE_NAME_REQUIRED = Lang(Keys.COMMAND_MINE_CREATE_NAME_REQUIRED)

    val COMMAND_MINE_CREATE_ALIAS_REQUIRED = Lang(Keys.COMMAND_MINE_CREATE_ALIAS_REQUIRED)

    val COMMAND_MINE_CREATE_SELECTION_UNSUPPORTED = Lang(Keys.COMMAND_MINE_CREATE_SELECTION_UNSUPPORTED)

    val COMMAND_MINE_CREATE_SUCCESSFUL = Lang(Keys.COMMAND_MINE_CREATE_SUCCESSFUL)

    val COMMAND_MINE_COMPOSITION_NOT_FOUND = Lang(Keys.COMMAND_MINE_COMPOSITION_NOT_FOUND)

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

    val COMMAND_MINE_CREATE_NAME_IN_USE = Lang(Keys.COMMAND_MINE_CREATE_NAME_IN_USE)

    val COMMAND_COMPOSITION_NAME_IN_USE = Lang(Keys.COMMAND_COMPOSITION_NAME_IN_USE)

    val COMMAND_RESET_MINE_REQUIRED = Lang(Keys.COMMAND_RESET_MINE_REQUIRED)

    val COMMAND_RESET_ALL_MINES_SUCCESS = Lang(Keys.COMMAND_RESET_ALL_MINES_SUCCESS)

    val COMMAND_RESET_MINE_SUCCESS = Lang(Keys.COMMAND_RESET_MINE_SUCCESS)

    val COMMAND_CLEAR_MINE_REQUIRED = Lang(Keys.COMMAND_CLEAR_MINE_REQUIRED)

    val COMMAND_CLEAR_ALL_MINES_SUCCESS = Lang(Keys.COMMAND_CLEAR_ALL_MINES_SUCCESS)

    val COMMAND_CLEAR_MINE_SUCCESS = Lang(Keys.COMMAND_CLEAR_MINE_SUCCESS)

    val MINE_DELETED = Lang(Keys.MINE_DELETED)

    val MINE_DISABLED = Lang(Keys.COMMAND_MINE_DISABLED)

    val COMPOSITION_SELECTOR_MENU_TITLE = Lang(Keys.COMPOSITION_SELECTOR_MENU_TITLE)

    val MAIN_MENU_TITLE = Lang(Keys.MAIN_MENU_TITLE)

    val MINE_LOADED = Lang(Keys.MINE_LOADED)

    val UNABLE_TO_LOAD_MINE = Lang(Keys.UNABLE_TO_LOAD_MINE)

    val MINE_COMPOSITION_CHANGED = Lang(Keys.MINE_COMPOSITION_CHANGED)

    object Keys {

        val MINE_NOT_FOUND = MineMeConfigValue(
                "$4Couldn't find a mine with name $1{name}$4!",
                MineMeConfigSource.MESSAGES,
                "misc.mine.notFound"
        )
        val WORLD_EDIT_SELECTION_REQUIRED = MineMeConfigValue(
                "$4You need to have a world edit selection!",
                MineMeConfigSource.MESSAGES,
                "command.creation.mine.worldEdit.selectionRequired"
        )
        val WORLD_EDIT_SELECTION_NOT_COMPLETE = MineMeConfigValue(
                "$4Your world edit needs to be complete!",
                MineMeConfigSource.MESSAGES,
                "command.creation.mine.worldEdit.selectionIncomplete"
        )

        val COMMAND_MINE_CREATE_NAME_REQUIRED = MineMeConfigValue(
                "$4You need to provide a name for the mine!",
                MineMeConfigSource.MESSAGES,
                "command.creation.mine.nameRequired"
        )
        val COMMAND_MINE_CREATE_COMPOSITION_REQUIRED = MineMeConfigValue(
                "$4You need to provide a composition for the mine!",
                MineMeConfigSource.MESSAGES,
                "command.creation.mine.compositionRequired"
        )
        val COMMAND_MINE_CREATE_ALIAS_REQUIRED = MineMeConfigValue(
                "$4You need to provide an alias for the mine!",
                MineMeConfigSource.MESSAGES,
                "command.creation.mine.aliasRequired"
        )

        val COMMAND_MINE_CREATE_SELECTION_UNSUPPORTED = MineMeConfigValue(
                "$4MineMe does not currently support this selection type :c ($1{type}$4)",
                MineMeConfigSource.MESSAGES,
                "command.creation.mine.selectionUnsupported"
        )

        val COMMAND_MINE_CREATE_NAME_IN_USE = MineMeConfigValue(
                "$4There is already a mine with this name ($1{name}$4)!",
                MineMeConfigSource.MESSAGES,
                "command.create.mine.nameInUse"
        )

        val COMMAND_MINE_CREATE_SUCCESSFUL = MineMeConfigValue(
                "$3Mine $5{name} $3successfully created! (&d{type}$3)",
                MineMeConfigSource.MESSAGES,
                "command.creation.mine.success"
        )
        val COMMAND_MINE_COMPOSITION_NOT_FOUND = MineMeConfigValue(
                "$4Composition $1{name} $4not found!",
                MineMeConfigSource.MESSAGES,
                "mine.composition.notFound"
        )
        val COMMAND_COMPOSITION_CREATE_NAME_REQUIRED = MineMeConfigValue(
                "$4You need to provide a name for the composition!",
                MineMeConfigSource.MESSAGES,
                "command.creation.composition.nameRequired"
        )
        val COMMAND_COMPOSITION_CREATE_ALIAS_REQUIRED = MineMeConfigValue(
                "$4You need to provide an alias for the composition!",
                MineMeConfigSource.MESSAGES,
                "command.creation.composition.aliasRequired"
        )
        val COMMAND_COMPOSITION_CREATE_SUCCESSFUL = MineMeConfigValue(
                "$3Composition $5{name} $3successfully created!",
                MineMeConfigSource.MESSAGES,
                "command.creation.composition.success"
        )
        val COMMAND_COMPOSITION_NAME_IN_USE = MineMeConfigValue(
                "$4There is already a composition with this name ($1{name}$4)!",
                MineMeConfigSource.MESSAGES,
                "command.creation.composition.nameInUse"
        )
        val COMMAND_MINE_LIST_HEADER = MineMeConfigValue(
                "$3Found mines:",
                MineMeConfigSource.MESSAGES,
                "command.list.mine.header"
        )
        val COMMAND_MINE_LIST_FOUND = MineMeConfigValue(
                "$1{name} $3($2{alias}$3, &d{type}) $3@ $1{world}",
                MineMeConfigSource.MESSAGES,
                "command.list.mine.found"
        )
        val COMMAND_MINE_LIST_EMPTY = MineMeConfigValue(
                "$4There are no mines loaded!",
                MineMeConfigSource.MESSAGES,
                "command.list.mine.empty"
        )
        val COMMAND_COMPOSITION_LIST_HEADER = MineMeConfigValue(
                "$3Found compositions:",
                MineMeConfigSource.MESSAGES,
                "command.list.compositions.header"
        )
        val COMMAND_COMPOSITION_LIST_FOUND = MineMeConfigValue(
                "$1{name} $3($2{alias}$3, isEmpty=&d{empty}$3)",
                MineMeConfigSource.MESSAGES,
                "command.list.compositions.found"
        )
        val COMMAND_COMPOSITION_LIST_EMPTY = MineMeConfigValue(
                "$4There are no mines compositions!",
                MineMeConfigSource.MESSAGES,
                "command.list.compositions.empty"
        )
        val COMMAND_RESET_MINE_REQUIRED = MineMeConfigValue(
                "$4You must provide a mine!",
                MineMeConfigSource.MESSAGES,
                "command.reset.nameRequired"
        )

        val COMMAND_RESET_ALL_MINES_SUCCESS = MineMeConfigValue(
                "$5Successfully reset all mines!",
                MineMeConfigSource.MESSAGES,
                "command.reset.resetAllMines"
        )
        val COMMAND_RESET_MINE_SUCCESS = MineMeConfigValue(
                "$5Successfully reset mine $1{alias} $3($2{name}$3)!",
                MineMeConfigSource.MESSAGES,
                "command.reset.resetMine"
        )
        val COMMAND_CLEAR_MINE_REQUIRED = MineMeConfigValue(
                "$4You must provide a mine!",
                MineMeConfigSource.MESSAGES,
                "command.clear.nameRequired"
        )
        val COMMAND_CLEAR_ALL_MINES_SUCCESS = MineMeConfigValue(
                "$5Successfully cleared all mines!",
                MineMeConfigSource.MESSAGES,
                "command.clear.clearedAllMines"
        )
        val COMMAND_CLEAR_MINE_SUCCESS = MineMeConfigValue(
                "The mine $1{name} $3has been successfully cleared!",
                MineMeConfigSource.MESSAGES,
                "command.clear.clearedMine"
        )

        val COMMAND_MINE_DISABLED = MineMeConfigValue(
                "Mine $1{name} $3was $5successfully disabled$3!",
                MineMeConfigSource.MESSAGES,
                "mine.disabled"
        )

        val MINE_DELETED = MineMeConfigValue(
                "Mine $1{name} $3was $5successfully deleted$3!",
                MineMeConfigSource.MESSAGES,
                "mine.deleted"
        )
        val COMPOSITION_SELECTOR_MENU_TITLE = MineMeConfigValue(
                "$1Select a new composition.",
                MineMeConfigSource.MESSAGES,
                "ui.compositionSelector.title"
        )
        val MAIN_MENU_TITLE = MineMeConfigValue(
                "{prefix}",
                MineMeConfigSource.MESSAGES,
                "ui.mainMenu.title"
        )
        val MINE_LOADED = MineMeConfigValue(
                "Mine $1{name} $3was $5successfully loaded$3!",
                MineMeConfigSource.MESSAGES,
                "mine.loaded"
        )
        val UNABLE_TO_LOAD_MINE = MineMeConfigValue(
                "$5We were not able to load mine $1{name}$5! Check the mine's file.",
                MineMeConfigSource.MESSAGES,
                "mine.unableToLoad"
        )
        val  MINE_COMPOSITION_CHANGED = MineMeConfigValue(
                "$3Mine composition changed to $1{alias} $3($2{name}$3).",
                MineMeConfigSource.MESSAGES,
                "mine.compositionChanged"
        )

    }


}
