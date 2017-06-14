package me.ddevil.mineme.craft.mine.loader

import com.sk89q.worldedit.regions.Region
import me.ddevil.mineme.craft.MineMe
import me.ddevil.mineme.craft.api.mine.MineFactory

abstract class AbstractFactory<R : Region>(val plugin: MineMe) : MineFactory<R>