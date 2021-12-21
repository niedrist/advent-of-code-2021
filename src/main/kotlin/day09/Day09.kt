package day09

import BasicDay
import util.FileReader

val heatmap = FileReader.asStrings("day09.txt")
    .map { line -> line.split("").filter { it != "" }.map { it.toInt() } }

fun main() = Day09.run()

object Day09 : BasicDay() {
    override fun part1(): Int {
        var riskLevel = 0
        for (i in heatmap.indices)
            for (j in heatmap[i].indices) {
                val adjacent: List<Int> = listOf(
                    heatmap.getOrElse(i) { emptyList() }.getOrElse(j - 1) { 10 },
                    heatmap.getOrElse(i) { emptyList() }.getOrElse(j + 1) { 10 },
                    heatmap.getOrElse(i - 1) { emptyList() }.getOrElse(j) { 10 },
                    heatmap.getOrElse(i + 1) { emptyList() }.getOrElse(j) { 10 }
                )
                if (adjacent.all { it > heatmap[i][j] })
                    riskLevel += 1 + heatmap[i][j]
            }
        return riskLevel
    }

    override fun part2(): Int {
        val newHeatmap = heatmap.map { it.toMutableList() }.toMutableList()
        val basinSizes = mutableListOf<Int>()
        for (i in heatmap.indices)
            for (j in heatmap[i].indices)
                if (heatmap[i][j] != 9)
                    basinSizes.add(getBasinSize(newHeatmap, i, j))
        basinSizes.sortDescending()
        return basinSizes.take(3).reduce { acc, i -> acc * i }
    }

    private fun getBasinSize(m: MutableList<MutableList<Int>>, i: Int, j: Int): Int {
        if (i < 0 || j < 0 || i > m.size - 1 || j > m[i].size - 1 || m[i][j] == 9)
            return 0
        m[i][j] = 9
        return 1 + getBasinSize(m, i + 1, j) +
                getBasinSize(m, i - 1, j) +
                getBasinSize(m, i, j + 1) +
                getBasinSize(m, i, j - 1)
    }
}


