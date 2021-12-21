package day05

import BasicDay
import util.FileReader
import util.toward
import java.lang.IllegalArgumentException
import kotlin.math.abs

val inputRegex = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex()
val vents = FileReader.asStrings("day05.txt")
    .map {
        inputRegex.matchEntire(it)?.destructured?.let { (x1, y1, x2, y2) ->
            Line(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())
        } ?: throw IllegalArgumentException("Invalid input")
    }

fun main() = Day05.run()

object Day05 : BasicDay() {
    override fun part1() = countOverlaps(vents, true)

    override fun part2() = countOverlaps(vents, false)

    private fun countOverlaps(vents: List<Line>, filterDiagonal: Boolean): Int {
        val filteredVents =
            if (filterDiagonal) vents.filter { (it.startX == it.endX) xor (it.startY == it.endY) } else vents
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
}

data class Line(val startX: Int, val startY: Int, val endX: Int, val endY: Int)