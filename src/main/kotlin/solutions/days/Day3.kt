package solutions.days

import java.io.File

object Day3 {
    internal fun getInput() = File("src/main/resources/inputs/day3.txt").readText()
    internal fun List<Int>.prod() = this.reduce(Int::times)
    internal fun String.executeMulCommand() = this.dropWhile { !it.isDigit() }
        .dropLastWhile { !it.isDigit() }
        .split(",")
        .map { it.toInt() }.prod()

    fun part1() = "mul\\([0-9]+,[0-9]+\\)".toRegex()
        .findAll(getInput())
        .map {
            it.value.executeMulCommand()
        }.sum()

    fun part2(): Int {
        val matches = "don't\\(\\)|do\\(\\)|mul\\([0-9]+,[0-9]+\\)".toRegex()
            .findAll(getInput()).map { it.value }

        var total = 0
        var doMul = true
        matches.forEach {
            when (it) {
                "do()" -> doMul = true
                "don't()" -> doMul = false
                else -> {
                    if (doMul) {
                        total += it.executeMulCommand()
                    }
                }
            }
        }
        return total
    }
}

fun main() {
    println(Day3.part1())
    println(Day3.part2())
}