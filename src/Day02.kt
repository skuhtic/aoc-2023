import kotlin.time.measureTime

fun main() {
    val day = "02"

    data class Draw(val red: Int, val green: Int, val blue: Int)

    fun part1(input: List<String>): Int {
        val games = input.map { line ->
            line.split(": ", limit = 2).let {
                it.first().substringAfter(" ").toInt() to
                        it.last().split("; ").map { sDraw ->
                            sDraw.split(", ").map { sColor ->
                                sColor.split(' ', limit = 2).let {
                                    it.last().first() to it.first()
                                }
                            }
                        }
            }.logPrint()
        }
        return TODO()
    }

    fun part2(input: List<String>): Int {
        return TODO()
    }

    readInput(day, "sample").let { checkSample(part1(it), 8) }

    readInput(day, "input").let {
        measureTime {
            part1(it).println()
            part2(it).println()
        }.let {
            println("Done in $it.")
        }
    }
}
