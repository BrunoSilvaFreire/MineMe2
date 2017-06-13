package me.ddevil.mineme.craft.util

import com.sk89q.worldedit.BlockVector2D
import com.sk89q.worldedit.Vector
import com.sk89q.worldedit.Vector2D
import me.ddevil.util.vector.DoubleVector2
import me.ddevil.util.vector.DoubleVector3
import me.ddevil.util.vector.IntVector2

fun DoubleVector3.toWE() = Vector(x, y, z)
fun DoubleVector2.toWE() = Vector2D(x, y)
fun IntVector2.toWE() = BlockVector2D(x, y)
fun BlockVector2D.toShiroi() = IntVector2(blockX, blockZ)
fun Vector.toShiroi() = DoubleVector3(x, y, z)
