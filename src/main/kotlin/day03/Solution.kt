package day03

import util.FileReader
import kotlin.math.pow

fun main() {
    val powerConsumption = FileReader.readResource("day03.txt").split("\n")
    part1(powerConsumption)
}

fun part1(powerConsumption: List<String>) {
    val counterMap = mutableMapOf<Int, Int>()
    powerConsumption.forEach {
        it.forEachIndexed { index, bit ->
            if (bit == '1' )
                counterMap[index] = counterMap.getOrDefault(index, 0) + 1
        }
    }
    val maxBinaryIndex = powerConsumption[0].length - 1
    var gamma = 0
    for (i in 0..maxBinaryIndex) {
        val bit = (counterMap[maxBinaryIndex - i]!!.div((powerConsumption.size / 2)))

        gamma += bit * 2.0.pow(i).toInt()

    }
    println( gamma * gamma.xor(4095))
}