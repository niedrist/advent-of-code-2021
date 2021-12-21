package day08

import BasicDay
import util.FileReader

val lines = FileReader.asStrings("day08.txt")
    .map { line -> line.split('|').map { it.trim().split(' ') } }
    .map { Line(it[0], it[1]) }

fun main() = Day08.run()

object Day08 : BasicDay() {
    override fun part1() = lines.sumOf { line -> line.digit.count { it.length in listOf(2, 3, 4, 7) } }

    override fun part2(): Any {
        TODO("Not yet implemented")
    }
}

data class Line(val segment: List<String>, val digit: List<String>)