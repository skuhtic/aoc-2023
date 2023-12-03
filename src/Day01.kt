import utils.checkSample
import utils.println
import utils.readInput
import kotlin.time.measureTime

fun main() {
    val day = "01"

    fun part1(input: List<String>): Int {
        return input.fold(0) { a, it ->
            a + it.first { it.isDigit() }.digitToInt() * 10 + it.last { it.isDigit() }.digitToInt()
        }
    }

    fun part2(input: List<String>): Int {
        val re =
            Regex(
                """([0123456789]|one|two|three|four|five|six|seven|eight|nine).+([0123456789]|one|two|three|four|five|six|seven|eight|nine)""",
                RegexOption.CANON_EQ
            )

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

        fun findNoDigit(digit: String): Int {
            if (digit[0].isDigit()) return digit[0].digitToInt()
            return when {
                digit.equals("one") -> 1
                digit.equals("two") -> 2
                digit.equals("three") -> 3
                digit.equals("four") -> 4
                digit.equals("five") -> 5
                digit.equals("six") -> 6
                digit.equals("seven") -> 7
                digit.equals("eight") -> 8
                digit.equals("nine") -> 9
                else -> error("Invalid digit: $digit")
            }
        }

        fun findNumber(input: String): Int {
            val (f, l) = re.matchEntire(input)?.destructured ?: error("No matches in: $input")
            return findNoDigit(f) * 10 + findNoDigit(l)
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
//            a + findDigit(it, false) * 10 + findDigit(it, true)
            a + findNumber(it)
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
