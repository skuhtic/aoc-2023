package day04

import utils.*

fun main() {
    val day = "04"

    fun part1(input: List<Card>): Int = input.sumOf {
        it.wining.intersect(it.numbers).fold(0) { a, i ->
            if (a == 0) 1 else a * 2
        }.toInt()
    }

    fun part2(input: List<Card>) = input.associate { it.no to 1 }.toMutableMap().also { counters ->
        input.forEachIndexed { i, card ->
            input.drop(i + 1).take(card.wining.intersect(card.numbers).count()).map { it.no }.let { inc ->
                inc.forEach { counters[it] = counters.getValue(it) + counters.getValue(card.no) }
            }
        }
    }.values.sum()

    fun List<String>.mapInput() = map { Card.parse(it) }

    readInput(day, "sample").mapInput().let {
        checkSample(part1(it), 13)
        checkSample(part2(it), 30)
    }

    readInput(day, "input").mapInput().let {
        measureSolution {
            part1(it).println()
            part2(it).println()
        }
    }
}

data class Card(val no: Int, val wining: Set<Int>, val numbers: Set<Int>) {
    companion object {
        fun parse(line: String): Card = line.split(": ").let { (c, x) ->
            x.split(" | ", limit = 2).let { nos ->
                Card(
                    no = c.substringAfterLast(' ').toInt(),
                    wining = nos.first().split(' ').mapNotNull { it.toIntOrNull() }.toSet(),
                    numbers = nos.last().split(' ').mapNotNull { it.toIntOrNull() }.toSet()
                )
            }
        }
    }
}