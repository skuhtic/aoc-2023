import kotlin.time.measureTime

fun main() {
    val day = "02"

    data class Draw(val red: Int, val green: Int, val blue: Int) {
        fun isLegit() = red <= 12 && green <= 13 && blue <= 14
        fun power() = red * green * blue
    }

    fun possibleWith(draws: Collection<Draw>) = Draw(
        draws.maxOf { it.red },
        draws.maxOf { it.green },
        draws.maxOf { it.blue },
    )

    fun getGames(input: List<String>) = input.associate { line ->
        line.split(": ", limit = 2).let { game ->
            game.first().substringAfter(" ").toInt() to
                    game.last().split("; ").map { sDraw ->
                        sDraw.split(", ").associate { sColor ->
                            sColor.split(' ', limit = 2).let {
                                it.last().first() to it.first().toInt()
                            }
                        }.let {
                            Draw(
                                it.getOrDefault('r', 0),
                                it.getOrDefault('g', 0),
                                it.getOrDefault('b', 0),
                            )
                        }
                    }
        }
    }

    fun part1(input: List<String>): Int {
        val games = getGames(input)
        val legitGames = games.filter {
            it.value.all { it.isLegit() }
        }
        return legitGames.keys.sum()
    }

    fun part2(input: List<String>): Int {
        val games = getGames(input)
        val possibleWith = games.mapValues { possibleWith(it.value) }
        return possibleWith.values.sumOf { it.power() }
    }

    readInput(day, "sample").let { checkSample(part1(it), 8) }
    readInput(day, "sample").let { checkSample(part2(it), 2286) }

    readInput(day, "input").let {
        measureTime {
            part1(it).println()
            part2(it).println()
        }.let {
            println("Done in $it.")
        }
    }
}
