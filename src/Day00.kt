fun main() {
    val day = "00"

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput(day, "sample")
    check(part1(testInput) == 1)

    val input = readInput(day, "input")
    part1(input).println()
    part2(input).println()
}
