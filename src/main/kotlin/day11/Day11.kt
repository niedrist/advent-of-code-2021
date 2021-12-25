package day11

import BasicDay
import util.FileReader

val energies = FileReader.asStrings("day11.txt")
    .map { line -> line.split("").filter { it != "" }.map { Octopus(it.toInt()) } }

fun main() = Day11.run()

object Day11 : BasicDay() {

    override fun part1(): Int {
        val p1Energies = energies.map { it.map { o -> o.copy() }.toMutableList() }.toMutableList()
        var flashes = 0
        (1..100).forEach {
            flashes +=step(p1Energies)
            p1Energies.resetFlashed()
        }
        return flashes
    }

    private fun step(energies: MutableList<MutableList<Octopus>>): Int {
        var flashes = 0
        for (i in energies.indices)
            for (j in energies[i].indices) {
                flashes += flash(energies, i, j)
            }
        return flashes
    }

    private fun flash(o: MutableList<MutableList<Octopus>>, i: Int, j: Int): Int {
        if (i >= 0 && j >= 0 && i <= o.size - 1 && j <= o[i].size - 1 && !o[i][j].flashed) {
            if (o[i][j].energy >= 9) {
                o[i][j].flashed = true
                o[i][j].energy = 0
                return 1 + flash(o, i - 1, j - 1) +
                        flash(o, i, j - 1) +
                        flash(o, i + 1, j - 1) +
                        flash(o, i - 1, j) +
                        flash(o, i + 1, j) +
                        flash(o, i - 1, j + 1) +
                        flash(o, i, j + 1) +
                        flash(o, i + 1, j + 1)
            } else  {
                o[i][j].energy += 1
            }
        }
        return 0
    }

    override fun part2(): Int {
        val p2Energies = energies.map { it.map { o -> o.copy() }.toMutableList() }.toMutableList()
        var i = 0
        while (!p2Energies.flashedSimultaneously()) {
            p2Energies.resetFlashed()
            step(p2Energies)
            i++
        }
        return i
    }

}

data class Octopus(var energy: Int, var flashed: Boolean = false)

fun List<List<Octopus>>.resetFlashed() { this.forEach{ it.forEach{ o -> o.flashed = false}}}
fun List<List<Octopus>>.flashedSimultaneously() = this.all { it.all { o -> o.flashed } }



