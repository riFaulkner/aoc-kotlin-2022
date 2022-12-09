fun main() {
    fun getElfCalorieTotals(input: List<String>): List<Int> {
        val elfTotals = ArrayList<Int>()
        var currentElfCalorieCount = 0

        input.forEach{
            if (it.equals("")) {
                elfTotals.add(currentElfCalorieCount)
                currentElfCalorieCount = 0
            } else {
                currentElfCalorieCount += it.toInt()
            }
        }
        elfTotals.add(currentElfCalorieCount)

        return elfTotals
    }

    fun getTotalForTopElfs(input: List<String>, numberOfElfs: Int = 1): Int {
        val elfTotals = getElfCalorieTotals(input).sortedDescending()

        return elfTotals.subList(0, numberOfElfs).sum()
    }

    fun part1(input: List<String>): Int {
        return getTotalForTopElfs(input)
    }

    fun part2(input: List<String>): Int {
        return getTotalForTopElfs(input, 3)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("inputs/Day01-example")
    part1(testInput).println()
    part2(testInput).println()

    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("inputs/Day01")
    part1(input).println()
    part2(input).println()
}
