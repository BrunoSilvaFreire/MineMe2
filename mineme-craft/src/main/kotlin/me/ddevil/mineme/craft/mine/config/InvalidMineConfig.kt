package me.ddevil.mineme.craft.mine.config

import me.ddevil.mineme.api.MineMeConstants
import java.io.File

class InvalidMineConfig(file: File) : AbstractMineConfig(file) {
    val missingFields: List<String>

    init {
        val errors = ArrayList<String>()
        val map = map
        if (map != null) {
            for (field in MineMeConstants.MINE_REQUIRED_FIELDS) {
                if (field !in map) {
                    errors += "Missing field \"$field\""
                }
            }
        } else {
            errors += "Malformated YAML"
        }
        missingFields = errors
    }
}