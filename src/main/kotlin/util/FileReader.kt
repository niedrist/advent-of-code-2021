package util

import java.io.File

object FileReader {
    fun readResource(path: String) = File(ClassLoader.getSystemResource(path).file).readText()
}