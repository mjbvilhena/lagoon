package com.mjbvilhena.lagoon.domain.item

const val NOTHING_SAVED_YET = "Nothing is saved yet."

const val TITLE_REQUIRED = "A title is required."

const val IN_TRASH = "In trash"

const val UNDO = "Undo"

fun homeItems(items: List<Item>): List<Item> = items.filter { !it.inTrash }.sortedByDescending { it.lastSavedAt }

data class DetailContent(
    val shown: Boolean,
    val title: String = "",
    val body: String = "",
    val notes: List<PersonalNote> = emptyList(),
    val tags: List<String> = emptyList(),
    val attachments: List<AttachmentRef> = emptyList(),
    val lastSavedAt: Long? = null,
    val sourceUrl: String? = null,
    val sourceDomain: String? = null,
    val sendingApp: String? = null,
    val ocrText: String? = null,
    val snapshotText: String? = null,
    val reminder: LocalSchedule? = null,
    val expiry: LocalSchedule? = null,
    val foggyPreview: Boolean = false,
)

/** A locked sensitive item shows none of its stored fields. Unlock is US-37. */
fun detailContent(
    item: Item,
    sensitiveUnlocked: Boolean,
): DetailContent {
    if (item.sensitive && !sensitiveUnlocked) {
        return DetailContent(shown = false)
    }
    return DetailContent(
        shown = true,
        title = item.title,
        body = item.body,
        notes = item.notes,
        tags = item.tags,
        attachments = item.attachments,
        lastSavedAt = item.lastSavedAt,
        sourceUrl = if (item.type == ItemType.LINK) item.sourceUrl else null,
        sourceDomain = item.sourceDomain,
        sendingApp = item.sendingApp,
        ocrText = item.ocrText,
        snapshotText = item.snapshotText,
        reminder = item.reminder,
        expiry = item.expiry,
        foggyPreview = item.foggyPreview,
    )
}

sealed class EditResult {
    data class Saved(
        val item: Item,
    ) : EditResult()

    data object TitleRequired : EditResult()
}

fun editItem(
    item: Item,
    title: String,
    body: String,
    notes: List<PersonalNote>,
    tags: List<String>,
    now: Long,
): EditResult {
    if (title.isBlank()) {
        return EditResult.TitleRequired
    }
    return EditResult.Saved(
        applyUserEdit(
            item,
            UserFieldEdit(
                title = title,
                body = body,
                notes = notes,
                tags = tags,
                reminder = item.reminder,
                expiry = item.expiry,
                attachments = item.attachments,
                sensitive = item.sensitive,
            ),
            now,
        ),
    )
}

fun replaceNoteText(
    notes: List<PersonalNote>,
    noteId: String,
    text: String,
): List<PersonalNote> =
    notes.map { note ->
        if (note.id == noteId) note.copy(text = text) else note
    }

fun addNote(
    notes: List<PersonalNote>,
    id: String,
    today: String,
    text: String,
): List<PersonalNote> = notes + PersonalNote(id = id, writtenOn = today, text = text)
