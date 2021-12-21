package day07

import BasicDay
import util.FileReader
import java.lang.Integer.min
import kotlin.math.abs

val horizontalPositions = FileReader.asStrings("day07.txt")[0].split(",").map { it.toInt() }

fun main() = Day07.run()

object Day07 : BasicDay() {
    override fun part1() = calculateMinFuel(horizontalPositions, false)

    override fun part2() = calculateMinFuel(horizontalPositions, true)

    private fun calculateMinFuel(horizontalPositions: List<Int>, stepsGetMoreExpensive: Boolean): Int {
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
}


