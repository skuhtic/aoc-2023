package day03

import utils.*

fun main() {
    val day = "03"

    fun part1(board: Board): Int = board.numbers.filter { (p, v) ->
        p.adjacent(v.toString().length).any {
            board.symbols.containsKey(it)
        }
    }.values.sum()

    fun part2(board: Board): Int = buildMap<Point2D, List<Int>> {
        val stars = board.symbols.filterValues { it == '*' }.keys
        board.numbers.forEach { (p, v) ->
            stars.intersect(p.adjacent(v.toString().length).toSet()).singleOrNull()?.let { star ->
                set(star, getOrDefault(star, emptyList()) + v)
            }
        }
    }.values.filter { it.size == 2 }.sumOf { it.first() * it.last() }

    readInput2(day, "sample").let { Board.parse(it) }.let {
        checkSample(part1(it), 4361)
        checkSample(part2(it), 467835)
    }

    readInput2(day, "input").let { Board.parse(it) }.let {
        measureSolution {
            part1(it).println()
            part2(it).println()
        }.let {
            println("Done in $it.")
        }
    }
}

typealias Point2D = Pair<Int, Int>
typealias Map2D<T> = Map<Point2D, T>

fun Point2D.adjacent(length: Int = 1) = (first - 1 to first + length).let {
    buildList {
        add(it.first to second)
        add(it.second to second)
        (it.first..it.second).forEach {
            add(it to second - 1)
            add(it to second + 1)
        }
    }
}

data class Board(val numbers: Map2D<Int>, val symbols: Map2D<Char>) {
    companion object {
        fun parse(input: List<String>): Board {
            val symbols = input.mapIndexed { y, line ->
                line.mapIndexedNotNull { x, c ->
                    if (c == '.' || c.isDigit()) null
                    else (x to y) to c
                }
            }.flatten().toMap()
            val numbers = input.mapIndexed { y, line ->
                val re = """\b(\d+)\b""".toRegex()
                re.findAll(line).toList().map {
                    (it.range.first() to y) to it.value.toInt()
                }
            }.flatten().toMap()
            return Board(numbers, symbols)
        }
    }
}
