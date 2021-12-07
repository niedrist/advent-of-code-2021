package day01

import util.FileReader

fun main() {
    val depths = FileReader.readResource("day01.txt").split("\n").map { it.toInt() }.toMutableList()
    part1(depths)
    part2(depths)
}

fun part1(depths: MutableList<Int>) {
    var counter = 0
    var prev = depths.removeFirst()
    depths.forEach {
        if (prev < it)
            counter++
        prev = it
    }
    println(counter)
}

fun part2(depths: MutableList<Int>) {
    var counter = 0
    var prev2 = depths[1]
    var prev1 = depths[2]
    var prevTotal = prev2 + prev1 + depths[0]
    depths.takeLast(depths.size - 3).forEach {
        val current = prev2 + prev1 + it
        if (prevTotal < current)
            counter++
        prevTotal = current
        prev2 = prev1
        prev1 = it
    }
    println(counter)
}