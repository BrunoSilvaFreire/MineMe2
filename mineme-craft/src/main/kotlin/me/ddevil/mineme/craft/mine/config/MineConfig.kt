package me.ddevil.mineme.craft.mine.config

import java.io.File

interface MineConfig {
    val file: File
    val map: Map<String, Any>?
}