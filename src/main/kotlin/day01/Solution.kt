package day01

import util.FileReader

fun main() {
    val d = FileReader.readResource("day01.txt").split("\n").map { it.toInt() }
    part1(d)
    part2(d)
}

fun part1(d: List<Int>) {
    println( d.zipWithNext().count { (x, y) -> x < y } )
}

fun part2(d: List<Int>) {
    println(d.windowed(3, 1).zipWithNext().count{ (x, y) -> x.sum() < y.sum()})
}