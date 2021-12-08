package day01

import util.FileReader

fun main() {
    val d = FileReader.asInts("day01.txt")
    println(part1(d))
    println(part2(d))
}

fun part1(d: List<Int>): Int = d.zipWithNext().count { (x, y) -> x < y }

fun part2(d: List<Int>): Int = d.windowed(3, 1).zipWithNext().count{ (x, y) -> x.sum() < y.sum() }