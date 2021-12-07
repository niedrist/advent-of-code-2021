package day02

import util.FileReader

fun main() {
    val depths = FileReader.readResource("day02.txt").split("\n").map {
        val splitted = it.split(" ")
        Command(direction = splitted[0], units = splitted[1].toInt())
    }
    part1(depths)
    part2(depths)
}

fun part1(commands: List<Command>) {
    val pos = Position()
    commands.forEach { pos.processCommand(it) }
    println(pos)
}

fun part2(commands: List<Command>) {
    val pos = Position()
    commands.forEach { pos.processAimCommand(it) }
    println(pos)
}

data class Command (val direction: String, val units: Int)
