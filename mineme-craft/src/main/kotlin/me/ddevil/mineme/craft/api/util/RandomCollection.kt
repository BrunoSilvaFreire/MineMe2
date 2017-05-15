package me.ddevil.mineme.craft.api.util

import java.util.*

class RandomCollection<E>
@JvmOverloads
constructor(private val random: Random = Random()) {
    private val map = TreeMap<Double, E>()
    private var total = 0.0

    fun add(weight: Double, result: E) {
        if (weight <= 0) return
        total += weight
        map.put(total, result)
    }

    operator fun next(): E {
        val value = random.nextDouble() * total
        return map.ceilingEntry(value).value
    }
}