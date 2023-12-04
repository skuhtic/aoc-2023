package day04

import utils.*

fun main() {
    val day = "04"

    fun part1(input: List<Card>): Int = TODO()

    fun part2(input: List<Card>): Int = TODO()

    fun List<String>.mapInput() = map { Card.parse(it) }

    readInput(day, "sample").mapInput().let {
        checkSample(part1(it), TODO())
        checkSample(part2(it), TODO())
    }

    readInput(day, "input").mapInput().let {
        measureSolution {
            part1(it).println()
            part2(it).println()
        }
    }
}

data class Card(val no: Int, val wining: List<Int>, val numbers: List<Int>) {
    companion object {
        fun parse(line: String): Card = line.split(": ").let {(c, x) ->
            x.split(" | ", limit = 2).let { nos ->
                Card(
                    no = c.substringAfter(' ').toInt(),
                    wining = nos.first().split( ' ').mapNotNull { it.toIntOrNull() },
                    numbers = nos.last().split( ' ').mapNotNull { it.toIntOrNull() }
                )
            }
        }
    }
}