package day05

import utils.*
import kotlin.math.min

fun main() {
    val day = "05"

    fun part1(input: Directions): Long = input.relations.fold(input.seeds) { acc, map ->
        map.relate(acc)
    }.min()

    fun partX(input: Directions): Long {
        var min = Long.MAX_VALUE
        input.seeds.chunked(2).map { it.first()..<it.first() + it.last() }
            .logPrint("INIT").forEach { range ->
                range.logPrint("range")
                range.forEach { seed ->
                    val result = input.relations.fold(seed) { acc, map -> map.relate(acc) }
                    if (result < min) min = result
                }
            }
        return min
    }

    fun part2(input: Directions): Long {
        buildMap<Long, Long> { }
        input.relations.logPrint("Original")
        val reversed = input.relations.reversed().map { it.reverse() }.logPrint("Reversed")
        val res = reversed.fold(0L) { acc, map -> map.relate(acc) }
        return res
    }

    fun List<String>.mapInput() = Directions.parse(this)

    readInput(day, "sample").mapInput().let {
        checkSample(part1(it), 35L)
        checkSample(part2(it), 46L)
    }

    readInput(day, "input").mapInput().let {
        measureSolution {
            part1(it).println()
            part2(it).println()
        }
    }
}

data class Directions(val seeds: List<Long>, val relations: List<Mappings>) {
    companion object {
        fun parse(input: List<String>) = input.listIterator().let { iter ->
            val seeds = iter.next().let { it.split(' ').drop(1).map { it.toLong() } }
            iter.next()
            buildList {
                while (iter.hasNext()) {
                    require(iter.next().endsWith("map:"))
                    buildMap {
                        while (iter.hasNext()) {
                            val next = iter.next()
                            if (next.isEmpty()) break
                            next.split(' ').map { it.toLong() }.let {
                                set(it[1]..<(it[1] + it[2]), it[0])
                            }
                        }
                    }.let { add(it) }
                }
            }.let { Directions(seeds, it.map { Mappings(it) }) }
        }
    }
}

@JvmInline
value class Mappings(val maps: Map<LongRange, Long>) {
    fun minDest() = maps.values.min()
    fun relate(input: List<Long>) = input.map { relate(it) }
    fun relate(input: Long) = maps.firstNotNullOfOrNull { if (input in it.key) it else null }?.let {
        it.value + input - it.key.first
    } ?: input

    fun reverse(): Mappings = Mappings(maps.map { it.value..<it.value + it.key.size to it.key.first }.toMap())

    companion object {
        val LongRange.size get() = last - first + 1
    }
}


