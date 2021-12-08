package day02

import util.FileReader

fun main() {
    val depths = FileReader.asStrings("day02.txt").map {
        val splitted = it.split(" ")
        Command(direction = splitted[0], units = splitted[1].toInt())
    }
    println(part1(depths))
    println(part2(depths))
}

fun part1(commands: List<Command>): Int {
    val pos = Position()
    commands.forEach { pos.processCommand(it) }
    return pos.getPos()
}

fun part2(commands: List<Command>): Int {
    val pos = Position()
    commands.forEach { pos.processAimCommand(it) }
    return pos.getPos()
}

data class Command (val direction: String, val units: Int)
