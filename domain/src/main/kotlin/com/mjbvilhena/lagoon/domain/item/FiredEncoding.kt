package com.mjbvilhena.lagoon.domain.item

fun encodeFired(keys: Set<String>): String = keys.sorted().joinToString("\n")

fun decodeFired(stored: String): Set<String> {
    if (stored.isEmpty()) {
        return emptySet()
    }
    return stored.split('\n').filter { it.isNotEmpty() }.toSet()
}
