package day12

import BasicDay
import util.FileReader

fun main() = Day12.run()

object Day12 : BasicDay() {

    private val connections = mutableMapOf<String, List<String>>()

    init {
        FileReader.asStrings("day12.txt").map { con ->
            con.split('-').let { l ->
                connections.put(l[0], connections.getOrDefault(l[0], emptyList()) + l[1])
                connections.put(l[1], connections.getOrDefault(l[1], emptyList()) + l[0])
            }
        }
    }

    override fun part1() = solve( false)

    override fun part2() = solve(true)

    fun solve(skip: Boolean): Int {
        var count = 0
        val queue = ArrayDeque<Triple<String, List<String>, Boolean>>()
        queue.add(Triple("start", listOf("start"), false))
        while (queue.isNotEmpty()) {
            val (curr, visited, twice) = queue.removeFirst()
            if (curr == "end") count++
            else
                connections[curr]!!.forEach { next ->
                    if (next !in visited) {
                        val newVisited = if (next == next.lowercase()) visited + next else visited
                        queue.add(Triple(next, newVisited, twice))
                    } else if (!twice && next != "start" && skip) {
                        queue.add(Triple(next, visited, true))
                    }
                }
        }
        return count
    }
}