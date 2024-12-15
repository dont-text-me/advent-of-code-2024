package solutions.days

import solutions.days.Day1.part1
import solutions.days.Day1.part2
import java.io.File
import kotlin.math.abs

object Day1 {
    internal fun parseInput(): Pair<List<Int>, List<Int>> {
        val inp = File("src/main/resources/inputs/day1.txt").readLines().filter { it.isNotBlank() }
        val lefts = inp.map { it.split("   ")[0].toInt() }
        val rights = inp.map { it.split("   ")[1].toInt() }
        return lefts to rights
    }

    fun part1(): Int {
        val (lefts, rights) = parseInput()
        return lefts.sorted().zip(rights.sorted()) { a, b -> abs(a - b) }.sum()
    }

    fun part2(): Int {
        val (lefts, rights) = parseInput()
        val counts = lefts.associateWith { l -> rights.count { it == l } }
        return lefts.sumOf { it * counts.getOrDefault(it, 0) }
    }
}

fun main() {
    println(part1())
    println(part2())
}