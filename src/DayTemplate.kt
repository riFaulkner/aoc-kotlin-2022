fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("inputs/DayXX-example")
    part1(testInput).println()
    part2(testInput).println()

    check(part1(testInput) == 1)
    check(part2(testInput) == 2)

    val input = readInput("inputs/DayXX")
    part1(input).println()
    part2(input).println()
}