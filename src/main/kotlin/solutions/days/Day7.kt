package solutions.days

import java.io.File
import kotlin.math.pow

object Day7 {
    data class Equation(val result: Long, val nums: List<Int>)

    internal fun parseInput(): List<Equation> = File("src/main/resources/inputs/day7.txt").readLines().map {
        Equation(
            it.split(":")[0].toLong(),
            it.split(":")[1].trim().split(" ").map { x -> x.toInt() }
        )
    }

    internal fun Equation.validate(): Boolean = (0..<2f.pow(this.nums.size - 1).toInt())
        .map { Integer.toBinaryString(it).padStart(this.nums.size - 1, '0') }
        .any { combo ->
            var total = this.nums.first().toLong()
            combo.forEachIndexed { index, op ->
                when (op) {
                    '0' -> total *= this.nums[index + 1].toLong()
                    '1' -> total += this.nums[index + 1].toLong()
                }
            }
            total == this.result
        }

    internal fun Equation.validateWithConcat(): Boolean =
        (0..<3f.pow(this.nums.size - 1).toInt()).map {
        it.toBase3()
            .padStart(this.nums.size - 1, '0')
        }.any { combo ->
            var total = this.nums.first().toLong()
            combo.forEachIndexed { index, op ->
                when (op) {
                    '0' -> total *= this.nums[index + 1].toLong()
                    '1' -> total += this.nums[index + 1].toLong()
                    '2' -> total = "$total${this.nums[index + 1]}".toLong()
                }
            }
            total == this.result
        }

    internal fun Int.toBase3(): String {
        var cur = this
        val out = mutableListOf<String>()
        while (cur >= 1) {
            out.add((cur % 3).toString())
            cur /= 3
        }
        return out.joinToString("").reversed()
    }

    fun part1(): Long = parseInput().filter { it.validate() }.sumOf { it.result }
    fun part2(): Long = parseInput().filter { it.validateWithConcat() }.sumOf { it.result }
}

fun main() {
    println(Day7.part1())
    println(Day7.part2())
}