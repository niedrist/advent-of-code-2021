package day05

import util.FileReader
import util.toward
import java.lang.IllegalArgumentException
import kotlin.math.abs

fun main() {
    val input = FileReader.asStrings("day05.txt")
    val inputRegex = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex()
    val vents = input.map { inputRegex.matchEntire(it)?.destructured?.let {
            (x1, y1, x2, y2) -> Line(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())
    }?: throw IllegalArgumentException("Invalid input")}
    println(part1(vents))
    println(part2(vents))
}

fun part1(vents: List<Line>) = countOverlaps(vents, true)

fun part2(vents: List<Line>) = countOverlaps(vents, false)

fun countOverlaps(vents: List<Line>, filterDiagonal: Boolean): Int {
    val filteredVents = if (filterDiagonal) vents.filter { (it.startX == it.endX) xor (it.startY == it.endY) } else vents
    val diagram = mutableMapOf<Pair<Int, Int>, Int>()
    for (vent in filteredVents) {
        val straight = (vent.startX == vent.endX || vent.startY == vent.endY)
        for (i in vent.startX toward vent.endX)
            for (j in vent.startY toward vent.endY) {
                if (straight || abs(i - vent.startX) == abs(j - vent.startY))
                    diagram[Pair(i, j)] = diagram.getOrDefault(Pair(i, j), 0) + 1
            }
    }
    return diagram.count { it.value > 1 }
}

data class Line(val startX: Int, val startY: Int, val endX: Int, val endY: Int)