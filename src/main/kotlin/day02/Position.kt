package day02

data class Position (var depth: Int = 0, var hPos: Int = 0, var aim: Int = 0) {
    fun processCommand(c: Command) {
        if (c.direction == "forward")
            hPos += c.units
        if (c.direction == "down")
            depth += c.units
        if (c.direction == "up")
            depth -= c.units
    }

    fun processAimCommand(c: Command) {
        if (c.direction == "forward") {
            hPos += c.units
            depth += aim * c.units
        }
        if (c.direction == "down")
            aim += c.units
        if (c.direction == "up")
            aim -= c.units
    }

    override fun toString(): String {
        return (depth * hPos).toString()
    }
}