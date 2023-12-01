import kotlin.time.measureTime

fun main() {
    val day = "01"

    fun part1(input: List<String>): Int {
        return input.fold(0) { a, it ->
            a + it.first { it.isDigit() }.digitToInt() * 10 + it.last { it.isDigit() }.digitToInt()
        }
    }

    fun part2(input: List<String>): Int {
        fun findDigit(part: String): Int? {
            if (part[0].isDigit()) return part[0].digitToInt()
            return when {
                part.startsWith("one") -> 1
                part.startsWith("two") -> 2
                part.startsWith("three") -> 3
                part.startsWith("four") -> 4
                part.startsWith("five") -> 5
                part.startsWith("six") -> 6
                part.startsWith("seven") -> 7
                part.startsWith("eight") -> 8
                part.startsWith("nine") -> 9
                else -> null
            }
        }

        fun findDigit(input: String, backwards: Boolean): Int {
            repeat(input.length) { n ->
                findDigit(input.drop(if (!backwards) n else input.length - n - 1))?.let {
                    return it
                }
            }
            error("No first digit in: $input")
        }

        return input.fold(0) { a, it ->
            a + findDigit(it, false) * 10 + findDigit(it, true)
        }
    }

    readInput(day, "sample").let { checkSample(part1(it), 142) }
    readInput(day, "sample_p2").let { checkSample(part2(it), 281) }

    readInput(day, "input").let {
        measureTime {
            part1(it).println()
            part2(it).println()
        }.let {
            println("Done in $it.")
        }
    }
}
