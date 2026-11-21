package com.mjbvilhena.lagoon.data.item

/**
 * The table Room creates for [ItemEntity]. Kept beside the migration so a
 * version-1 vault can reach the same shape.
 */
internal const val CURRENT_ITEM_TABLE =
    "CREATE TABLE IF NOT EXISTS `item` (" +
        "`id` TEXT NOT NULL, " +
        "`type` TEXT NOT NULL, " +
        "`title` TEXT NOT NULL, " +
        "`body` TEXT NOT NULL, " +
        "`sourceUrl` TEXT, " +
        "`createdAt` INTEGER NOT NULL, " +
        "`updatedAt` INTEGER NOT NULL, " +
        "`lastSavedAt` INTEGER NOT NULL, " +
        "`inTrash` INTEGER NOT NULL, " +
        "`trashedAt` INTEGER, " +
        "`trashChangedAt` INTEGER, " +
        "`reminderDate` TEXT, " +
        "`reminderHour` INTEGER, " +
        "`reminderMinute` INTEGER, " +
        "`expiryDate` TEXT, " +
        "`expiryHour` INTEGER, " +
        "`expiryMinute` INTEGER, " +
        "`userEditedAt` INTEGER, " +
        "`sensitive` INTEGER NOT NULL, " +
        "`firedOccurrences` TEXT NOT NULL, " +
        "`sourceDomain` TEXT, " +
        "`sendingApp` TEXT, " +
        "`ocrText` TEXT, " +
        "`snapshotText` TEXT, " +
        "`extractedText` TEXT, " +
        "`foggyPreview` INTEGER NOT NULL, " +
        "PRIMARY KEY(`id`))"

internal const val PERSONAL_NOTE_TABLE =
    "CREATE TABLE IF NOT EXISTS `personal_note` (" +
        "`id` TEXT NOT NULL, " +
        "`itemId` TEXT NOT NULL, " +
        "`writtenOn` TEXT NOT NULL, " +
        "`text` TEXT NOT NULL, " +
        "PRIMARY KEY(`id`), " +
        "FOREIGN KEY(`itemId`) REFERENCES `item`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )"

internal const val PERSONAL_NOTE_INDEX =
    "CREATE INDEX IF NOT EXISTS `index_personal_note_itemId` ON `personal_note` (`itemId`)"

internal const val ITEM_TAG_TABLE =
    "CREATE TABLE IF NOT EXISTS `item_tag` (" +
        "`itemId` TEXT NOT NULL, " +
        "`name` TEXT NOT NULL, " +
        "PRIMARY KEY(`itemId`, `name`), " +
        "FOREIGN KEY(`itemId`) REFERENCES `item`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )"

internal const val ITEM_TAG_INDEX =
    "CREATE INDEX IF NOT EXISTS `index_item_tag_itemId` ON `item_tag` (`itemId`)"

internal const val ATTACHMENT_TABLE =
    "CREATE TABLE IF NOT EXISTS `attachment` (" +
        "`id` TEXT NOT NULL, " +
        "`itemId` TEXT NOT NULL, " +
        "`displayName` TEXT NOT NULL, " +
        "PRIMARY KEY(`id`), " +
        "FOREIGN KEY(`itemId`) REFERENCES `item`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )"

internal const val ATTACHMENT_INDEX =
    "CREATE INDEX IF NOT EXISTS `index_attachment_itemId` ON `attachment` (`itemId`)"

internal const val TOMBSTONE_TABLE =
    "CREATE TABLE IF NOT EXISTS `tombstone` (" +
        "`itemId` TEXT NOT NULL, " +
        "`writtenAt` INTEGER NOT NULL, " +
        "`trashedAt` INTEGER NOT NULL, " +
        "`trashChangedAt` INTEGER NOT NULL, " +
        "PRIMARY KEY(`itemId`))"
