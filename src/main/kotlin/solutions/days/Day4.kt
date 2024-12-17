package solutions.days

import java.io.File

object Day4 {
    internal fun parseInput() = File("src/main/resources/inputs/day4test.txt").readLines()

    fun getDiagonalLtR(grid: List<String>, startRow: Int, startCol: Int): String {
        val out = mutableListOf<Char>()
        var curRow = startRow
        var curCol = startCol
        while (curRow < grid.size && curCol < grid.first().length) {
            out.add(grid[curRow][curCol])
            curRow++
            curCol++
        }
        return out.joinToString("")
    }

    fun getDiagonalRtL(grid: List<String>, startRow: Int, startCol: Int): String {
        val out = mutableListOf<Char>()
        var curRow = startRow
        var curCol = startCol
        while (curRow >= 0 && curCol >= 0) {
            out.add(grid[curRow][curCol])
            curRow--
            curCol--
        }
        return out.joinToString("")
    }

    fun part1(): Int {
        val patternRtl = "SAMX".toRegex()
        val patternLtr = "XMAS".toRegex()
        val rows = parseInput()
        val columns = (0..<rows.first().length).map { idx -> rows.map { it[idx] }.joinToString("") }
        val diagsLtR = (0..<rows.size).map { getDiagonalLtR(rows, it, 0) } + (0..<rows.first().length).map {
            getDiagonalLtR(
                rows,
                0,
                it
            )
        }
        val diagsRtL = (0..<rows.size).map { getDiagonalRtL(rows, it, rows.first().lastIndex) } +
                (0..<rows.first().length).map { getDiagonalRtL(rows, rows.lastIndex, it) }
        return listOf(rows, columns, diagsLtR, diagsRtL).sumOf { inps ->
            inps.sumOf { it ->
                patternLtr.findAll(it).toList().size + patternRtl.findAll(it).toList().size
            }
        }
    }
}

fun main() {
    println(Day4.part1())
}