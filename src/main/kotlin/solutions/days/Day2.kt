package solutions.days

import java.io.File
import kotlin.math.max

object Day2 {
    internal fun parseInputs(): List<List<Int>> {
        val inp = File("src/main/resources/inputs/day2.txt").readLines().filter { it.isNotBlank() }
        return inp.map { line -> line.split(" ").map { it.toInt() } }
    }

    internal fun List<Int>.isSafe(): Boolean {
        val checkIncr = this.zip(this.drop(1)) { a, b -> a - b <= -1 && a - b >= -3 }.all { it }
        val checkDecr = this.zip(this.drop(1)) { a, b -> a - b >= 1 && a - b <= 3 }.all { it }
        return checkIncr || checkDecr
    }

    fun List<Int>.isSafeDampened() =
        (0..this.size).map { this.take(max(0, it - 1)) + this.drop(it) }.any { it.isSafe() }

    fun part1() = parseInputs().count { it.isSafe() }

    fun part2() = parseInputs().count { it.isSafeDampened() }
}

fun main() {
    println(Day2.part1())
    println(Day2.part2())
}