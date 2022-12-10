fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { assignments ->
            val assignmentPair = getAssignmentPair(assignments)
            isFullyInclusivePair(assignmentPair)
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { assignments ->
            val assignmentPair = getAssignmentPair(assignments)
            hasOverlap(assignmentPair)
        }
    }

    val testInput = readInput("inputs/Day04-example")
    part1(testInput).println()
    part2(testInput).println()

    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("inputs/Day04")
    part1(input).println()
    part2(input).println()
}

fun hasOverlap(assignmentPair: Pair<String, String>): Int {
    // if the aLow is > bHigh or if aHigh is < bLow
    val (aLow, aHigh) = assignmentPair.first.splitToInts("-")
    val (bLow, bHigh) = assignmentPair.second.splitToInts("-")

    if (aLow > bHigh || aHigh < bLow) {
        return 0
    }

    return 1
}

fun isFullyInclusivePair(assignmentPair: Pair<String, String>): Int {
    if (pairAContainsB(assignmentPair.first, assignmentPair.second) || pairAContainsB(assignmentPair.second, assignmentPair.first)){
        return 1
    }
    return 0
}

fun getAssignmentPair(fullAssignment: String): Pair<String, String> {
    val (a, b) = fullAssignment.split(",")
    return a to b
}

fun pairAContainsB(a: String, b: String): Boolean {
    val (aLow, aHigh) = a.splitToInts("-")
    val (bLow, bHigh) = b.splitToInts("-")

    if (aLow <= bLow) {
        if (bHigh <= aHigh) {
            return true
        }
    }
    return false
}

fun String.splitToInts(delimiter: String) = run { this.split(delimiter).map { it.toInt() }.toList() }