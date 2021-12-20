package day07

import util.FileReader
import java.lang.Integer.min
import kotlin.math.abs

fun main() {
    val input = FileReader.asStrings("day07.txt")[0].split(",").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}

fun part1(horizontalPositions: List<Int>) = calculateMinFuel(horizontalPositions, false)

fun part2(horizontalPositions: List<Int>) = calculateMinFuel(horizontalPositions, true)

fun calculateMinFuel(horizontalPositions: List<Int>, stepsGetMoreExpensive: Boolean): Int {
    val min = horizontalPositions.minOrNull()!!
    val max = horizontalPositions.maxOrNull()!!
    var minFuel = Integer.MAX_VALUE
    IntRange(min, max).forEach{ targetPos ->
        var currentFuel = 0
        for (currentPos in horizontalPositions)
            if (stepsGetMoreExpensive)
                for (i in 1..abs(targetPos - currentPos))
                    currentFuel += i
            else
                currentFuel += abs(targetPos - currentPos)
        minFuel = min(minFuel, currentFuel)

    }
    return minFuel
}
