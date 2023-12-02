val searchValues = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        .mapIndexed { index, value -> SearchValue((index + 1).toString(), value) }

fun firstNumberForLine(line: String) =
        (searchValues.map { Result(line.indexOf(it.digit), it.digit) } + searchValues.map { Result(line.indexOf(it.word), it.digit) })
                .filter { it.index > -1 }
                .minByOrNull { it.index }
                ?.digit

fun lastNumberForLine(line: String) =
        (searchValues.map { Result(line.lastIndexOf(it.digit), it.digit) } + searchValues.map { Result(line.lastIndexOf(it.word), it.digit) })
                .filter { it.index > -1 }
                .maxByOrNull { it.index }
                ?.digit

fun part2(input: List<String>) = input.mapNotNull { line ->
    val first = firstNumberForLine(line)
    val last = lastNumberForLine(line)

    if (first != null && last != null) (first + last).toInt() else null
}.sum()

fun part1(input: List<String>) = input
        .mapNotNull { line ->
            val first = line.firstOrNull { it.isDigit() }
            val last = line.lastOrNull { it.isDigit() }
            if (first != null && last != null) String(charArrayOf(first, last)).toInt() else null
        }.sum()


fun main() {
    val input = readToList("day01.txt")
    part1(input).print()
    part2(input).print()
}

data class Result(val index: Int, val digit: String)
data class SearchValue(val digit: String, val word: String)
