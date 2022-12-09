fun main() {
    fun part1(input: List<String>): Int {
        val scorePerRound = input.map {round ->
            val opponentChoice = getChoiceValue(round.subSequence(0, 1).toString())
            val myChoice = getChoiceValue(round.subSequence(2,3).toString())
            scoreRound(opponentChoice, myChoice)
        }
        return scorePerRound.sum()
    }

    fun part2(input: List<String>): Int {
        val scorePerRound = input.map {round ->
            val opponentChoice = getChoiceValue(round.subSequence(0, 1).toString())
            val myChoice = generateMyChoice(opponentChoice, round.subSequence(2,3).toString())
            scoreRound(opponentChoice, myChoice)
        }
        return scorePerRound.sum()
    }

    runTests()
    val testInput = readInput("inputs/Day02-example")
    part1(testInput).println()
    part2(testInput).println()

    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("inputs/Day02")
    part1(input).println()
    part2(input).println()
}

fun runTests() {
    readInput("inputs/Day02-tests").forEach {round ->
        val opponentChoice = getChoiceValue(round.subSequence(0, 1).toString())
        val myChoice = getChoiceValue(round.subSequence(2,3).toString())
        val expectedScore = round.subSequence(4, 5).toString().toInt()
        check(scoreRound(opponentChoice, myChoice) == expectedScore)
    }
}

fun getChoiceValue(choice: String): Int {
    return when (choice) {
        "A", "X" -> {
            1
        }
        "B", "Y" -> {
            2
        }
        "C", "Z" -> {
            3
        }
        else -> throw IllegalArgumentException("Invalid entry")
    }
}

fun generateMyChoice(oppChoiceValue: Int, outcomeRequired: String): Int {
    /**
     * X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win.
     *
     * X = -1
     * Y = 0
     * Z = +1
     */
    val choice = when(outcomeRequired) {
        "X" -> oppChoiceValue-1
        "Y" -> oppChoiceValue
        "Z" -> oppChoiceValue + 1
        else -> throw IllegalArgumentException("Invalid input")
    }

    if (choice == 0) {
        return 3
    }
    if (choice == 4) {
        return 1
    }
    return choice
}

fun scoreRound(oppChoiceValue: Int, myChoiceValue: Int): Int {
    /**
     * The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
     * plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
     */
    var roundScoring = myChoiceValue

    if (oppChoiceValue == myChoiceValue) {
        roundScoring += 3
    }
    if ((oppChoiceValue + 1) % 3  == myChoiceValue % 3) {
        roundScoring += 6
    }

    return roundScoring
}