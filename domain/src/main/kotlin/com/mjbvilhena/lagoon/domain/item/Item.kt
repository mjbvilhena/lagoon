package com.mjbvilhena.lagoon.domain.item

enum class ItemType {
    LINK,
    NOTE,
    IMAGE,
    PDF,
}

/** A dated personal-note entry. [writtenOn] is `YYYY-MM-DD`. */
data class PersonalNote(
    val id: String,
    val writtenOn: String,
    val text: String,
) {
    init {
        require(id.isNotEmpty())
        require(writtenOn.matches(DATE))
    }
}

/** Attachment identity. The bytes are vault content and are not stored here. */
data class AttachmentRef(
    val id: String,
    val displayName: String,
) {
    init {
        require(id.isNotEmpty())
    }
}

/**
 * A local civil date and a time of day. This is not a UTC instant.
 * [date] is `YYYY-MM-DD`.
 */
data class LocalSchedule(
    val date: String,
    val hour: Int,
    val minute: Int,
) {
    init {
        require(date.matches(DATE))
        require(hour in 0..23)
        require(minute in 0..59)
    }
}

data class Item(
    val id: ItemId,
    val type: ItemType,
    val title: String,
    val body: String,
    val sourceUrl: String?,
    val notes: List<PersonalNote>,
    val tags: List<String>,
    val sensitive: Boolean,
    val createdAt: Long,
    val updatedAt: Long,
    val userEditedAt: Long?,
    val lastSavedAt: Long,
    val inTrash: Boolean,
    val trashedAt: Long?,
    val trashChangedAt: Long?,
    val reminder: LocalSchedule?,
    val expiry: LocalSchedule?,
    val firedOccurrences: Set<String>,
    val sourceDomain: String?,
    val sendingApp: String?,
    val ocrText: String?,
    val snapshotText: String?,
    val extractedText: String?,
    val attachments: List<AttachmentRef>,
    val foggyPreview: Boolean,
)

internal val DATE = Regex("\\d{4}-\\d{2}-\\d{2}")
