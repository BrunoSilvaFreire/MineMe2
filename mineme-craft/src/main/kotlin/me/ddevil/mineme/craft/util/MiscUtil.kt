package me.ddevil.mineme.craft.util

import co.aikar.taskchain.TaskChainTasks
import me.ddevil.shiroi.craft.plugin.ShiroiPlugin
import me.ddevil.shiroi.craft.util.set
import me.ddevil.util.Serializable
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

fun Serializable.saveTo(file: File, plugin: ShiroiPlugin<*, *>) {
    plugin.chainFactory.newChain<Any>().asyncFirst<Any>(
            TaskChainTasks.FirstTask<Any> {
                val config = YamlConfiguration()
                config.set(serialize())
                config.save(file)
                return@FirstTask null

            }
    ).execute()
}