package day06

import util.FileReader

fun main() {
    val input = FileReader.asStrings("day06.txt")[0].split(",").map { it.toInt() }
    val initialAges = input.groupBy { it }.mapValues { it.value.count().toLong() }
    println(part1(initialAges))
    println(part2(initialAges))
}

fun part1(initialAges: Map<Int, Long>) = calculatePopulation(initialAges.toMutableMap(), 80)

fun part2(initialAges: Map<Int, Long>) = calculatePopulation(initialAges.toMutableMap(), 256)

fun calculatePopulation(ages: MutableMap<Int, Long>, amountDays: Int): Long {
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