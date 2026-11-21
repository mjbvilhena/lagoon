package com.mjbvilhena.lagoon.data.item

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "item")
data class ItemEntity(
    @PrimaryKey val id: String,
    val type: String,
    val title: String,
    val body: String,
    val sourceUrl: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val lastSavedAt: Long,
    val inTrash: Boolean,
    val trashedAt: Long?,
    val trashChangedAt: Long?,
    val reminderDate: String?,
    val reminderHour: Int?,
    val reminderMinute: Int?,
    val expiryDate: String?,
    val expiryHour: Int?,
    val expiryMinute: Int?,
    val userEditedAt: Long?,
    val sensitive: Boolean,
    val firedOccurrences: String,
    val sourceDomain: String?,
    val sendingApp: String?,
    val ocrText: String?,
    val snapshotText: String?,
    val extractedText: String?,
    val foggyPreview: Boolean,
)

@Entity(
    tableName = "personal_note",
    foreignKeys = [
        ForeignKey(
            entity = ItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index("itemId")],
)
data class PersonalNoteEntity(
    @PrimaryKey val id: String,
    val itemId: String,
    val writtenOn: String,
    val text: String,
)

@Entity(
    tableName = "item_tag",
    primaryKeys = ["itemId", "name"],
    foreignKeys = [
        ForeignKey(
            entity = ItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index("itemId")],
)
data class ItemTagEntity(
    val itemId: String,
    val name: String,
)

@Entity(
    tableName = "attachment",
    foreignKeys = [
        ForeignKey(
            entity = ItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index("itemId")],
)
data class AttachmentEntity(
    @PrimaryKey val id: String,
    val itemId: String,
    val displayName: String,
)

@Entity(tableName = "tombstone")
data class TombstoneEntity(
    @PrimaryKey val itemId: String,
    val writtenAt: Long,
    val trashedAt: Long,
    val trashChangedAt: Long,
)

data class ItemWithChildren(
    @Embedded val item: ItemEntity,
    @Relation(parentColumn = "id", entityColumn = "itemId")
    val notes: List<PersonalNoteEntity>,
    @Relation(parentColumn = "id", entityColumn = "itemId")
    val tags: List<ItemTagEntity>,
    @Relation(parentColumn = "id", entityColumn = "itemId")
    val attachments: List<AttachmentEntity>,
)
