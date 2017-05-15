package me.ddevil.mineme.api.storage

import me.ddevil.mineme.api.composition.MineComposition
import me.ddevil.mineme.api.mine.MineInfo

interface StorageManager {
    fun getComposition(name: String): MineComposition?

    fun getMine(name: String): MineInfo?
}

