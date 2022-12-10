fun main() {
    fun part1(input: List<String>): Int {
        val rucksackPriorities = input.map {rucksack ->
           val (comp1, comp2) = getRucksackCompartments(rucksack)
            calculatePriority(comp1, comp2)
        }
        return rucksackPriorities.sum()
    }

    fun part2(input: List<String>): Int {
        val groupCharacters = getGroupBadgeCharacters(input).map {
            characterToPriorityValue(it)
        }
        return groupCharacters.sum()
    }

    val testInput = readInput("inputs/Day03-example")
    part1(testInput).println()
    part2(testInput).println()

    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("inputs/Day03")
    part1(input).println()
    part2(input).println()
}

fun getRucksackCompartments(rucksack: String): Pair<String, String> {
    val midPoint = rucksack.length/2
    return rucksack.subSequence(0, midPoint).toString() to rucksack.subSequence(midPoint,rucksack.length).toString()
}

fun calculatePriority(comp1: String, comp2: String): Int {
    val comp1Map = comp1.map {
        it to true
    }.toMap()

    comp2.forEach {
        if (comp1Map.contains(it)) {
           return characterToPriorityValue(it)
        }
    }

    return 0
}

fun characterToPriorityValue(char: Char): Int {
    // 'A' ascii value is 65, offset for that, then because we want lowercase to be a lower priority value
    // subtract 26 to shift the whole set
    var offset = 64 - 26
    if (char.isLowerCase()) {
        offset = 96
    }
    return char.toString().first().code - offset
}

fun getGroupBadgeCharacters(input: List<String>): List<Char> {
    val groups = mutableListOf<List<String>>()

    val currentGroup = mutableListOf<String>()
    input.forEach {
        currentGroup.add(it)
        if (currentGroup.size == 3) {
            groups.add(ArrayList(currentGroup))
            currentGroup.clear()
        }
    }

    return groups.map {
        findGroupBadgeCharacter(it)
    }
}

fun findGroupBadgeCharacter(group: List<String>): Char {
    if (group.size != 3) throw IllegalArgumentException("Incorrect Group Size")

    val comp1Map = group[0].map {
        it to false
    }.toMap().toMutableMap()

    group[1].forEach {
        if (comp1Map.contains(it)) {
            comp1Map[it] = true
        }
    }
    group[2].forEach {
        if (comp1Map.get(it) == true) {
            return it
        }
    }
    throw Exception("Group does not have a badge")
}