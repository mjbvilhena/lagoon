package com.mjbvilhena.lagoon.domain.item

/**
 * An item id is assigned once. A live item and a tombstone both count as used,
 * so a deleted item's id is not given to a new item.
 */
class ItemIds(
    used: Set<String> = emptySet(),
) {
    private val usedIds = used.toMutableSet()

    fun used(): Set<String> = usedIds.toSet()

    fun allocate(draw: () -> String): ItemId {
        while (true) {
            val next = draw()
            require(next.isNotEmpty()) { "an item id is not empty" }
            if (usedIds.add(next)) {
                return ItemId(next)
            }
        }
    }

    fun remember(id: ItemId) {
        usedIds.add(id.value)
    }
}

data class ItemId(
    val value: String,
) {
    init {
        require(value.isNotEmpty()) { "an item id is not empty" }
    }
}
