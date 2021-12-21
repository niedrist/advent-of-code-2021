package day01

import BasicDay
import util.FileReader

val d = FileReader.asInts("day01.txt")

fun main() = Day01.run()


object Day01: BasicDay() {
    override fun part1() = d.zipWithNext().count { (x, y) -> x < y }

    override fun part2() = d.windowed(3, 1).zipWithNext().count{ (x, y) -> x.sum() < y.sum() }
}