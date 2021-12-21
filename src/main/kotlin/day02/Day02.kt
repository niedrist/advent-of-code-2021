package day02

import BasicDay
import util.FileReader

val commands = FileReader.asStrings("day02.txt").map {
    val splitted = it.split(" ")
    Command(direction = splitted[0], units = splitted[1].toInt())
}

fun main() = Day02.run()

object Day02 : BasicDay() {
    override fun part1(): Int {
        val pos = Position()
        commands.forEach { pos.processCommand(it) }
        return pos.getPos()
    }

    override fun part2(): Int {
        val pos = Position()
        commands.forEach { pos.processAimCommand(it) }
        return pos.getPos()
    }
}

data class Command(val direction: String, val units: Int)
