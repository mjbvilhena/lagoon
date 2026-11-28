package com.mjbvilhena.lagoon.domain.vault

/**
 * Unicode default case fold: CaseFolding.txt statuses C and F.
 * The table is Unicode 18.0.0. It is not a locale fold.
 */
object UnicodeCaseFold {
    private val mappings: Map<Int, IntArray> by lazy { load() }

    fun fold(text: String): String {
        val folded = StringBuilder()
        var index = 0
        while (index < text.length) {
            val codePoint = text.codePointAt(index)
            val mapped = mappings[codePoint]
            if (mapped == null) {
                folded.appendCodePoint(codePoint)
            } else {
                mapped.forEach { folded.appendCodePoint(it) }
            }
            index += Character.charCount(codePoint)
        }
        return folded.toString()
    }

    private fun load(): Map<Int, IntArray> {
        val resource =
            UnicodeCaseFold::class.java.getResourceAsStream("/unicode/case-fold-c-f.txt")
                ?: error("Unicode case fold table is missing")
        val table = HashMap<Int, IntArray>()
        resource.bufferedReader().useLines { lines ->
            lines.forEach { line ->
                if (line.isBlank()) {
                    return@forEach
                }
                val parts = line.split(' ')
                val source = parts[0].toInt(16)
                val mapped = IntArray(parts.size - 1) { parts[it + 1].toInt(16) }
                table[source] = mapped
            }
        }
        return table
    }
}
