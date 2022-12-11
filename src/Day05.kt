fun main() {
    fun part1(input: List<String>): String {
        // Get the starting position into a usable state
        // Follow all the moves
        // Return the top element of each stack
        return input.size.toString()
    }

    fun part2(input: List<String>): String {
        return input.size.toString()
    }

    val testInput = readInput("inputs/Day05-example")
    part1(testInput).println()
//    part2(testInput).println()

    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "🤷")

//    val input = readInput("inputs/DayXX")
//    part1(input).println()
//    part2(input).println()
}