package day03

import util.FileReader
import kotlin.math.pow

fun main() {
    val powerConsumption = FileReader.readResource("day03.txt").split("\n").toMutableList()
    println(part1(powerConsumption))
    println(part2(powerConsumption))
}

fun part1(powerConsumption: List<String>): Int {
    val counterMap = mutableMapOf<Int, Int>()
    powerConsumption.forEach {
        it.forEachIndexed { index, bit ->
            if (bit == '1' )
                counterMap[index] = counterMap.getOrDefault(index, 0) + 1
        }
    }
    val maxBinaryIndex = powerConsumption[0].length - 1
    var gamma = 0
    for (i in 0..maxBinaryIndex)
        gamma += (counterMap.getOrDefault(maxBinaryIndex - i, 0).div((powerConsumption.size / 2))) * 2.0.pow(i).toInt()

    return gamma * gamma.xor(2.0.pow(maxBinaryIndex + 1).toInt() - 1)
}

fun part2(powerConsumption: MutableList<String>): Int {
    var l1 = powerConsumption.toMutableList()
    var l2 = powerConsumption.toMutableList()
    var i = 0
    while(l1.size > 1) {
        val mostCommon = bitCriteria(l1, i, true)
        l1 = l1.filter { it[i] == mostCommon }.toMutableList()
        i++
    }
    i = 0
    while(l2.size > 1) {
        val leastCommon = bitCriteria(l2, i, false)
        l2 = l2.filter { it[i] == leastCommon }.toMutableList()
        i++
    }
    return  binaryStringToInt(l1.first()) * binaryStringToInt(l2.first())
}

fun binaryStringToInt(s: String): Int {
    var num = 0
    for (i in s.indices)
        num += (s[i].code - 48) * 2.0.pow(s.length - 1 - i).toInt()
    return num
}



fun bitCriteria(bitsList: List<String>, index: Int, mostCommon: Boolean): Char {
    val bits0 = bitsList.count { it[index] == '0'}
    val bits1 = bitsList.count { it[index] == '1'}
    if (mostCommon)
        return if (bits0 > bits1) '0' else '1'
    return if (bits0 <= bits1) '0' else '1'
}