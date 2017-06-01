package me.ddevil.mineme.craft.mine.loader

import com.sk89q.worldedit.regions.Region
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.mine.MineLoader

abstract class AbstractLoader<R : Region>(val plugin: MineMe) : MineLoader<R>