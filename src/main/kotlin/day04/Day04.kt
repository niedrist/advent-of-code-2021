package day04

import BasicDay
import util.FileReader

const val LINE_SIZE = 5
const val BINGO_SIZE = 25
const val BINGO_MARKER = -1

val input = FileReader.asStrings("day04.txt").filter { it != "" }.toMutableList()
val draws = input.removeFirst().split(',').map { it.toInt() }
val bingoNumbers = mutableListOf<Int>()
var bingos = emptyList<Bingo>()



fun main() {
    input.forEach { line -> bingoNumbers.addAll(line.trim().split("\\s+".toRegex()).map { it.toInt() }) }
    bingos = bingoNumbers.withIndex().groupBy { it.index / BINGO_SIZE }.map { it.value.map { it.value } }.map { Bingo(it.toMutableList(), false) }
    Day04.run()
}


object Day04 : BasicDay() {
    override fun part1(): Int {
        for (draw in draws)
            for (bingo in bingos) {
                bingo.values.replaceAll { number -> if (number == draw) BINGO_MARKER else number }
                if (bingo.checkWin())
                    return bingo.getSumRemaining() * draw
            }
        return -1
    }

    override fun part2(): Int {
        var latestWinningBoard: Bingo? = null
        var latestDraw: Int = -1
        for (draw in draws) {
            for (bingo in bingos) {
                if (!bingo.won) {
                    bingo.values.replaceAll { number -> if (number == draw) BINGO_MARKER else number }
                    if (bingo.checkWin()) {
                        bingo.won = true
                        latestWinningBoard = bingo
                        latestDraw = draw
                    }
                }
            }
        }
        latestWinningBoard?.let { return it.getSumRemaining() * latestDraw }
        return -1
    }
}

data class Bingo(val values: MutableList<Int>, var won: Boolean) {
    fun checkWin(): Boolean {
        for (i in values.indices) {
            if (i % LINE_SIZE == 0)
                if (values[i] + values[i + 1] + values[i + 2] + values[i + 3] + values[i + 4] == BINGO_MARKER * LINE_SIZE) return true
            if ((i / LINE_SIZE) % BINGO_SIZE == 0)
                if (values[i] + values[i + LINE_SIZE] + values[i + 2 * LINE_SIZE] + values[i + 3 * LINE_SIZE] + values[i + 4 * LINE_SIZE] == BINGO_MARKER * LINE_SIZE) return true
        }
        return false
    }

    fun getSumRemaining(): Int {
        var sum = 0
        for (v in values) {
            if (v != -1)
                sum += v
        }
        return sum
    }
}