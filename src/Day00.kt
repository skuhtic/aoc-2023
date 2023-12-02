import kotlin.time.measureTime

fun main() {
    val day = "00"

    fun part1(input: List<String>): Int {
        return TODO()
    }

    fun part2(input: List<String>): Int {
        return TODO()
    }

    readInput(day, "sample").let { checkSample(part1(it), TODO()) }
//    readInput(day, "sample_p2").let { checkSample(part2(it), TODO()) }

    readInput(day, "input").let {
        measureTime {
            part1(it).println()
            part2(it).println()
        }.let {
            println("Done in $it.")
        }
    }
}
