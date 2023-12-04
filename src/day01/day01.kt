package day01

import utils.*

fun main() {
    val day = "01"

    fun part1(input: List<String>): Int = input.fold(0) { a, it ->
        a + it.first { it.isDigit() }.digitToInt() * 10 + it.last { it.isDigit() }.digitToInt()
    }

    fun part2(input: List<String>): Int = input.fold(0) { a, it ->
        a + findLineNumber(it)
    }

    readInput(day, "sample").let { checkSample(part1(it), 142) }
    readInput(day, "sample_p2").let { checkSample(part2(it), 281) }

    readInput(day, "input").let {
        measureSolution {
            part1(it).println()
            part2(it).println()
        }
    }
}

val numbers = setOf(
    "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
)

fun String.parseToInt() = if (length == 1) first().digitToInt() else numbers.indexOf(this)
fun findLineNumber(line: String) =
    "${line.findAnyOf(numbers)!!.second.parseToInt()}${line.findLastAnyOf(numbers)!!.second.parseToInt()}".toInt()
