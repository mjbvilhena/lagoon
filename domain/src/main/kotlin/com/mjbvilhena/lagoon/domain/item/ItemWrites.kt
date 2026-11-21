package com.mjbvilhena.lagoon.domain.item

import java.util.UUID

/** Later of [now] and one millisecond after [previous]. The value does not go backwards. */
fun monotonicUpdatedAt(
    previous: Long?,
    now: Long,
): Long {
    if (previous == null) {
        return now
    }
    return maxOf(now, previous + 1L)
}

fun newItemId(draw: () -> String = { UUID.randomUUID().toString() }): String = draw()

fun createItem(
    ids: ItemIds,
    type: ItemType,
    title: String,
    body: String,
    now: Long,
    draw: () -> String = { UUID.randomUUID().toString() },
    sourceUrl: String? = null,
    notes: List<PersonalNote> = emptyList(),
    tags: List<String> = emptyList(),
    sensitive: Boolean = false,
    reminder: LocalSchedule? = null,
    expiry: LocalSchedule? = null,
    sourceDomain: String? = null,
    sendingApp: String? = null,
    ocrText: String? = null,
    snapshotText: String? = null,
    extractedText: String? = null,
    attachments: List<AttachmentRef> = emptyList(),
    foggyPreview: Boolean = false,
): Item =
    Item(
        id = ids.allocate(draw),
        type = type,
        title = title,
        body = body,
        sourceUrl = sourceUrl,
        notes = notes,
        tags = tags,
        sensitive = sensitive,
        createdAt = now,
        updatedAt = now,
        userEditedAt = null,
        lastSavedAt = now,
        inTrash = false,
        trashedAt = null,
        trashChangedAt = null,
        reminder = reminder,
        expiry = expiry,
        firedOccurrences = emptySet(),
        sourceDomain = sourceDomain,
        sendingApp = sendingApp,
        ocrText = ocrText,
        snapshotText = snapshotText,
        extractedText = extractedText,
        attachments = attachments,
        foggyPreview = foggyPreview,
    )

data class UserFieldEdit(
    val title: String,
    val body: String,
    val notes: List<PersonalNote>,
    val tags: List<String>,
    val reminder: LocalSchedule?,
    val expiry: LocalSchedule?,
    val attachments: List<AttachmentRef>,
    val sensitive: Boolean,
)

/** A user edit sets [Item.userEditedAt] and [Item.updatedAt] to the same instant. */
fun applyUserEdit(
    item: Item,
    edit: UserFieldEdit,
    now: Long,
): Item {
    val stamp = monotonicUpdatedAt(item.updatedAt, now)
    return item.copy(
        title = edit.title,
        body = edit.body,
        notes = edit.notes,
        tags = edit.tags,
        reminder = edit.reminder,
        expiry = edit.expiry,
        attachments = edit.attachments,
        sensitive = edit.sensitive,
        updatedAt = stamp,
        userEditedAt = stamp,
    )
}

/**
 * A system write sets [Item.updatedAt] and does not set [Item.userEditedAt].
 * It does not clear trash and it does not move last-saved.
 */
fun applySystemWrite(
    item: Item,
    now: Long,
    change: (Item) -> Item,
): Item {
    val changed = change(item)
    return changed.copy(
        id = item.id,
        createdAt = item.createdAt,
        lastSavedAt = item.lastSavedAt,
        userEditedAt = item.userEditedAt,
        updatedAt = monotonicUpdatedAt(item.updatedAt, now),
        inTrash = item.inTrash,
        trashedAt = item.trashedAt,
        trashChangedAt = item.trashChangedAt,
    )
}

/** Last-saved moves on a repeat share. The cross-phone rule is the later value. */
fun markRepeatShare(
    item: Item,
    now: Long,
): Item {
    val written = applySystemWrite(item, now) { it }
    return written.copy(lastSavedAt = maxOf(item.lastSavedAt, now))
}

/**
 * Trash fields as accepted ADR-0002 defines them when undo ends.
 * This does not write a tombstone.
 */
fun moveToTrash(
    item: Item,
    endedAt: Long,
): Item =
    item.copy(
        inTrash = true,
        trashedAt = endedAt,
        trashChangedAt = endedAt,
        updatedAt = monotonicUpdatedAt(item.updatedAt, endedAt),
    )

data class Tombstone(
    val itemId: ItemId,
    val writtenAt: Long,
    val trashedAt: Long,
    val trashChangedAt: Long,
)

/** The permanent-delete record. The caller stores it. This does not decide when it is written. */
fun tombstoneFor(
    item: Item,
    writtenAt: Long,
): Tombstone {
    val trashedAt = item.trashedAt
    val trashChangedAt = item.trashChangedAt
    require(item.inTrash && trashedAt != null && trashChangedAt != null) {
        "a tombstone ends a trash record"
    }
    return Tombstone(
        itemId = item.id,
        writtenAt = writtenAt,
        trashedAt = trashedAt,
        trashChangedAt = trashChangedAt,
    )
}

/** Fired state is sticky per occurrence. A key that either side recorded stays fired. */
fun mergeFiredOccurrences(
    left: Set<String>,
    right: Set<String>,
): Set<String> = left + right

/** Last-saved merges to the later value. */
fun mergeLastSaved(
    left: Long,
    right: Long,
): Long = maxOf(left, right)
