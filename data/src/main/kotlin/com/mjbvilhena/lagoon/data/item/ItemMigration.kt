package com.mjbvilhena.lagoon.data.item

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mjbvilhena.lagoon.domain.item.LegacyItemRow
import com.mjbvilhena.lagoon.domain.item.encodeFired
import com.mjbvilhena.lagoon.domain.item.migrateLegacyItem
import java.time.ZoneId

const val LEGACY_ITEM_TABLE =
    "CREATE TABLE IF NOT EXISTS `item` (" +
        "`id` TEXT NOT NULL, " +
        "`type` TEXT NOT NULL, " +
        "`title` TEXT NOT NULL, " +
        "`body` TEXT NOT NULL, " +
        "`sourceUrl` TEXT, " +
        "`createdAt` INTEGER, " +
        "`updatedAt` INTEGER, " +
        "`lastSavedAt` INTEGER, " +
        "`inTrash` INTEGER NOT NULL, " +
        "`trashedAt` INTEGER, " +
        "`trashChangedAt` INTEGER, " +
        "`reminderDate` TEXT, " +
        "`expiryDate` TEXT, " +
        "`timeHour` INTEGER, " +
        "`timeMinute` INTEGER, " +
        "PRIMARY KEY(`id`))"

val ITEM_MIGRATION_1_2: Migration =
    object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            migrateLegacyDatabase(db, System.currentTimeMillis(), ZoneId.systemDefault())
        }
    }

fun migrateLegacyDatabase(
    db: SupportSQLiteDatabase,
    migrationAt: Long,
    zone: ZoneId,
) {
    val rows = readLegacyRows(db)
    db.execSQL("ALTER TABLE `item` RENAME TO `item_legacy`")
    db.execSQL(CURRENT_ITEM_TABLE)
    rows.forEach { row ->
        val migrated = migrateLegacyItem(row, migrationAt, zone)
        db.execSQL(
            "INSERT INTO `item` (" +
                "`id`, `type`, `title`, `body`, `sourceUrl`, `createdAt`, `updatedAt`, `lastSavedAt`, " +
                "`inTrash`, `trashedAt`, `trashChangedAt`, `reminderDate`, `reminderHour`, `reminderMinute`, " +
                "`expiryDate`, `expiryHour`, `expiryMinute`, `userEditedAt`, `sensitive`, `firedOccurrences`, " +
                "`sourceDomain`, `sendingApp`, `ocrText`, `snapshotText`, `extractedText`, `foggyPreview`" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            arrayOf<Any?>(
                row.id,
                row.type,
                row.title,
                row.body,
                row.sourceUrl,
                migrated.createdAt,
                migrated.updatedAt,
                migrated.lastSavedAt,
                if (migrated.inTrash) 1 else 0,
                migrated.trashedAt,
                migrated.trashChangedAt,
                migrated.reminder?.date,
                migrated.reminder?.hour,
                migrated.reminder?.minute,
                migrated.expiry?.date,
                migrated.expiry?.hour,
                migrated.expiry?.minute,
                migrated.userEditedAt,
                0,
                encodeFired(migrated.firedOccurrences),
                null,
                null,
                null,
                null,
                null,
                0,
            ),
        )
    }
    db.execSQL("DROP TABLE `item_legacy`")
    db.execSQL(PERSONAL_NOTE_TABLE)
    db.execSQL(PERSONAL_NOTE_INDEX)
    db.execSQL(ITEM_TAG_TABLE)
    db.execSQL(ITEM_TAG_INDEX)
    db.execSQL(ATTACHMENT_TABLE)
    db.execSQL(ATTACHMENT_INDEX)
    db.execSQL(TOMBSTONE_TABLE)
}

private fun readLegacyRows(db: SupportSQLiteDatabase): List<LegacyItemRow> {
    val cursor =
        db.query(
            "SELECT `id`, `type`, `title`, `body`, `sourceUrl`, `createdAt`, `updatedAt`, `lastSavedAt`, " +
                "`inTrash`, `trashedAt`, `trashChangedAt`, `reminderDate`, `expiryDate`, `timeHour`, `timeMinute` " +
                "FROM `item`",
        )
    cursor.use {
        val rows = mutableListOf<LegacyItemRow>()
        while (it.moveToNext()) {
            rows.add(
                LegacyItemRow(
                    id = it.getString(0),
                    type = it.getString(1),
                    title = it.getString(2),
                    body = it.getString(3),
                    sourceUrl = it.getStringOrNull(4),
                    createdAt = it.getLongOrNull(5),
                    updatedAt = it.getLongOrNull(6),
                    lastSavedAt = it.getLongOrNull(7),
                    inTrash = it.getInt(8) != 0,
                    trashedAt = it.getLongOrNull(9),
                    trashChangedAt = it.getLongOrNull(10),
                    reminderDate = it.getStringOrNull(11),
                    expiryDate = it.getStringOrNull(12),
                    timeHour = it.getIntOrNull(13),
                    timeMinute = it.getIntOrNull(14),
                ),
            )
        }
        return rows
    }
}

private fun android.database.Cursor.getStringOrNull(index: Int): String? = if (isNull(index)) null else getString(index)

private fun android.database.Cursor.getLongOrNull(index: Int): Long? = if (isNull(index)) null else getLong(index)

private fun android.database.Cursor.getIntOrNull(index: Int): Int? = if (isNull(index)) null else getInt(index)
