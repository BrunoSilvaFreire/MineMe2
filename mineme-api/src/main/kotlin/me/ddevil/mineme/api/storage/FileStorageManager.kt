package me.ddevil.mineme.api.storage

import me.ddevil.json.parse.JsonParser
import me.ddevil.mineme.api.MineMeAPI
import me.ddevil.mineme.api.MineMeConstants
import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.api.mine.MineInfo
import java.io.File

class FileStorageManager : StorageManager {


    val mainFolder: File
    lateinit var api: MineMeAPI

    constructor(mainFolder: File, api: MineMeAPI) {
        this.mainFolder = mainFolder
        this.api = api
        this.jsonParser = JsonParser()
        this.loadedCompositions = HashMap<String, MineComposition>()
        this.loadedMines = HashMap<String, MineInfo>()
        if (!mainFolder.exists()) {
            mainFolder.mkdirs()
        }
        this.minesFolder = File(mainFolder, MineMeConstants.MINE_FOLDER_NAME)
        if (minesFolder.exists()) {
            minesFolder.mkdirs()
        }
        this.compositionsFolder = File(mainFolder, MineMeConstants.COMPOSITION_FOLDER_NAME)
        if (compositionsFolder.exists()) {
            compositionsFolder.mkdirs()
        }
    }

    private val jsonParser: JsonParser
    val minesFolder: File
    val compositionsFolder: File

    private val loadedCompositions: HashMap<String, MineComposition>
    private val loadedMines: HashMap<String, MineInfo>

    override fun getComposition(name: String): MineComposition? = loadedCompositions.getOrPut(name) {
        val compositionFile = getCompositionFile(name)
        if (!compositionFile.exists()) {
            return null
        }
        val json = jsonParser.parseObject(compositionFile)
        return@getOrPut MineComposition(json)
    }

    fun getCompositionFile(name: String) = File(
            compositionsFolder,
            "$name${MineMeConstants.STORAGE_FILE_EXTENSION}"
    )

    override fun getMine(name: String): MineInfo? = loadedMines.getOrPut(name) {
        val mineFile = getMineFile(name)
        if (!mineFile.exists()) {
            return null
        }
        val json = jsonParser.parseObject(mineFile)
        return@getOrPut MineInfo(json, api)
    }

    fun getMineFile(name: String) = File(
            minesFolder,
            "$name${MineMeConstants.STORAGE_FILE_EXTENSION}"
    )
}