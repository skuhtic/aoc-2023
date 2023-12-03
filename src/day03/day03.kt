package day03

import utils.*

fun main() {
    val day = "03"
    fun part1(board: Board): Int {
        board.logPrint()
        return 1
    }
    fun part2(board: Board): Int = TODO()

    readInput2(day, "sample").let { Board.parse(it) }.let {
        checkSample(part1(it), 8)
        checkSample(part2(it), 2286)
    }

    readInput(day, "input").let { Board.parse(it) }.let {
        measureSolution {
            part1(it).println()
            part2(it).println()
        }.let {
            println("Done in $it.")
        }
    }
}

data class Pos2D<T>(val pos: Pair<Int, Int>, val value: T)

typealias Map2D<T> = Map<Pair<Int, Int>, T>

data class Board(val numbers: Pos2D<Int>?, val symbols: Map2D<Char>) {
    companion object {
        fun parse(input: List<String>): Board {
            val symbols = input.mapIndexed { y, line ->
                line.mapIndexedNotNull { x, c ->
                    if (c == '.' || c.isDigit()) null
                    else (x to y) to c
                }
            }.flatten().toMap()
            val numbers = input.mapIndexed { y, line ->
                line.filterIndexed { i, c -> c.isDigit() }
                line.foldIndexed<List<Map<String, Int>>>(emptyList()) { i, acc, c ->
                    acc
                }
            }
            return Board(null, symbols)
        }
    }
}
