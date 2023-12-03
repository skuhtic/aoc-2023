package day02

import utils.*

fun main() {
    val day = "02"

    fun part1(games: List<Game>): Int = games.filter { it.isLegit() }.sumOf { it.id }

    fun part2(games: List<Game>): Int = games.map { it.possibleWith() }.sumOf { it.power() }

    readInput2(day, "sample").map { Game.parse(it) }.let {
        checkSample(part1(it), 8)
        checkSample(part2(it), 2286)
    }

    readInput2(day, "input").map { Game.parse(it) }.let {
        measureSolution {
            part1(it).println()
            part2(it).println()
        }
    }
}

data class Game(val id: Int, val draws: List<Draw>) {
    fun isLegit() = draws.all { it.isLegit() }
    fun possibleWith() = with(draws) { Draw(maxOf { it.red }, maxOf { it.green }, maxOf { it.blue }) }

    companion object {
        fun parse(input: String) = input.split(": ", limit = 2).let { (game, draws) ->
            Game(game.substringAfter(' ').toInt(), Draw.parseAll(draws))
        }
    }
}

data class Draw(val red: Int, val green: Int, val blue: Int) {
    fun isLegit() = red <= 12 && green <= 13 && blue <= 14
    fun power() = red * green * blue

    companion object {
        fun parseAll(input: String): List<Draw> = input.split("; ").map { parse(it) }
        fun parse(input: String): Draw = input.split(", ").associate { sColor ->
            sColor.split(' ', limit = 2).let {
                it.last() to it.first().toInt()
            }
        }.let {
            Draw(
                it.getOrDefault("red", 0),
                it.getOrDefault("green", 0),
                it.getOrDefault("blue", 0),
            )
        }
    }
}

