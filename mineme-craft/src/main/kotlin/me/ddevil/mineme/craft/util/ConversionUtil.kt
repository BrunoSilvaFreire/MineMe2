package me.ddevil.mineme.craft.util

import com.sk89q.worldedit.BlockVector2D
import com.sk89q.worldedit.Vector
import com.sk89q.worldedit.Vector2D
import me.ddevil.util.math.vector.DoubleVector3
import me.ddevil.util.math.vector.IntVector2
import me.ddevil.util.math.vector.Vector2
import me.ddevil.util.math.vector.Vector3

fun Vector3<*>.toWE() = Vector(x.toDouble(), y.toDouble(), z.toDouble())
fun Vector2<*>.toWE() = Vector2D(x.toDouble(), y.toDouble())

fun Vector2<Int>.toWE() = BlockVector2D(x, y)

fun BlockVector2D.toShiroi() = IntVector2(blockX, blockZ)
fun Vector.toShiroi() = DoubleVector3(x, y, z)
