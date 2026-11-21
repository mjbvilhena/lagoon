package com.mjbvilhena.lagoon.data.item

import com.mjbvilhena.lagoon.domain.item.AttachmentRef
import com.mjbvilhena.lagoon.domain.item.Item
import com.mjbvilhena.lagoon.domain.item.ItemId
import com.mjbvilhena.lagoon.domain.item.ItemType
import com.mjbvilhena.lagoon.domain.item.LocalSchedule
import com.mjbvilhena.lagoon.domain.item.PersonalNote
import com.mjbvilhena.lagoon.domain.item.Tombstone
import com.mjbvilhena.lagoon.domain.item.decodeFired
import com.mjbvilhena.lagoon.domain.item.encodeFired

class ItemStore(
    private val database: VaultDatabase,
) {
    fun save(item: Item) {
        val dao = database.items()
        database.runInTransaction {
            dao.insertItem(item.toEntity())
            dao.deleteNotes(item.id.value)
            dao.deleteTags(item.id.value)
            dao.deleteAttachments(item.id.value)
            if (item.notes.isNotEmpty()) {
                dao.insertNotes(item.notes.map { it.toEntity(item.id.value) })
            }
            if (item.tags.isNotEmpty()) {
                dao.insertTags(item.tags.map { ItemTagEntity(item.id.value, it) })
            }
            if (item.attachments.isNotEmpty()) {
                dao.insertAttachments(item.attachments.map { it.toEntity(item.id.value) })
            }
        }
    }

    fun saveTombstone(tombstone: Tombstone) {
        database.items().insertTombstone(
            TombstoneEntity(
                itemId = tombstone.itemId.value,
                writtenAt = tombstone.writtenAt,
                trashedAt = tombstone.trashedAt,
                trashChangedAt = tombstone.trashChangedAt,
            ),
        )
    }

    fun item(id: ItemId): Item? = database.items().item(id.value)?.toItem()

    fun tombstone(id: ItemId): Tombstone? {
        val row = database.items().tombstone(id.value) ?: return null
        return Tombstone(
            itemId = ItemId(row.itemId),
            writtenAt = row.writtenAt,
            trashedAt = row.trashedAt,
            trashChangedAt = row.trashChangedAt,
        )
    }

    fun usedIds(): Set<String> = database.items().usedIds().toSet()
}

private fun Item.toEntity(): ItemEntity =
    ItemEntity(
        id = id.value,
        type = type.name,
        title = title,
        body = body,
        sourceUrl = sourceUrl,
        createdAt = createdAt,
        updatedAt = updatedAt,
        lastSavedAt = lastSavedAt,
        inTrash = inTrash,
        trashedAt = trashedAt,
        trashChangedAt = trashChangedAt,
        reminderDate = reminder?.date,
        reminderHour = reminder?.hour,
        reminderMinute = reminder?.minute,
        expiryDate = expiry?.date,
        expiryHour = expiry?.hour,
        expiryMinute = expiry?.minute,
        userEditedAt = userEditedAt,
        sensitive = sensitive,
        firedOccurrences = encodeFired(firedOccurrences),
        sourceDomain = sourceDomain,
        sendingApp = sendingApp,
        ocrText = ocrText,
        snapshotText = snapshotText,
        extractedText = extractedText,
        foggyPreview = foggyPreview,
    )

private fun PersonalNote.toEntity(itemId: String): PersonalNoteEntity =
    PersonalNoteEntity(id = id, itemId = itemId, writtenOn = writtenOn, text = text)

private fun AttachmentRef.toEntity(itemId: String): AttachmentEntity = AttachmentEntity(id = id, itemId = itemId, displayName = displayName)

private fun ItemWithChildren.toItem(): Item {
    val row = item
    return Item(
        id = ItemId(row.id),
        type = ItemType.valueOf(row.type),
        title = row.title,
        body = row.body,
        sourceUrl = row.sourceUrl,
        notes = notes.map { PersonalNote(it.id, it.writtenOn, it.text) },
        tags = tags.map { it.name },
        sensitive = row.sensitive,
        createdAt = row.createdAt,
        updatedAt = row.updatedAt,
        userEditedAt = row.userEditedAt,
        lastSavedAt = row.lastSavedAt,
        inTrash = row.inTrash,
        trashedAt = row.trashedAt,
        trashChangedAt = row.trashChangedAt,
        reminder = schedule(row.reminderDate, row.reminderHour, row.reminderMinute),
        expiry = schedule(row.expiryDate, row.expiryHour, row.expiryMinute),
        firedOccurrences = decodeFired(row.firedOccurrences),
        sourceDomain = row.sourceDomain,
        sendingApp = row.sendingApp,
        ocrText = row.ocrText,
        snapshotText = row.snapshotText,
        extractedText = row.extractedText,
        attachments = attachments.map { AttachmentRef(it.id, it.displayName) },
        foggyPreview = row.foggyPreview,
    )
}

private fun schedule(
    date: String?,
    hour: Int?,
    minute: Int?,
): LocalSchedule? {
    if (date == null || hour == null || minute == null) {
        return null
    }
    return LocalSchedule(date, hour, minute)
}
