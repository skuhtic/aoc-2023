import utils.*

fun main() {
    val day = "00"
    require(day.toInt() in 1..25)

    fun part1(input: List<String>): Int = TODO()

    fun part2(input: List<String>): Int = TODO()

    fun List<String>.mapInput() = this

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
