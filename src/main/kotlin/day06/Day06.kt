package day06

import BasicDay
import util.FileReader

val initialAges = FileReader.asStrings("day06.txt")[0]
    .split(",")
    .map { it.toInt() }
    .groupBy { it }
    .mapValues { it.value.count().toLong() }

fun main() = Day06.run()

object Day06 : BasicDay() {
    override fun part1() = calculatePopulation(initialAges.toMutableMap(), 80)

    override fun part2() = calculatePopulation(initialAges.toMutableMap(), 256)

    private fun calculatePopulation(ages: MutableMap<Int, Long>, amountDays: Int): Long {
        IntRange(1, amountDays).forEach { _ ->
            val countNewFish = ages.getOrDefault(0, 0)
            IntRange(0, 7).forEach { day ->
                ages[day] = ages.getOrDefault(day + 1, 0)
            }
            ages[6] = ages.getOrDefault(6, 0) + countNewFish
            ages[8] = countNewFish
        }
        return ages.values.sum()
    }
}
