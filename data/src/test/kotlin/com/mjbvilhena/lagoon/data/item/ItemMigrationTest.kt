package com.mjbvilhena.lagoon.data.item

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mjbvilhena.lagoon.domain.item.ItemId
import com.mjbvilhena.lagoon.domain.item.ItemIds
import com.mjbvilhena.lagoon.domain.item.ItemType
import com.mjbvilhena.lagoon.domain.item.createItem
import com.mjbvilhena.lagoon.domain.item.moveToTrash
import com.mjbvilhena.lagoon.domain.item.tombstoneFor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.time.LocalDate
import java.time.ZoneOffset

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ItemMigrationTest {
    @Test
    fun migrationKeepsRowsAndFillsIdentityFields() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val name = "legacy-vault"
        context.deleteDatabase(name)
        val file = context.getDatabasePath(name)
        file.parentFile?.mkdirs()
        val sqlite = SQLiteDatabase.openOrCreateDatabase(file, null)
        sqlite.execSQL(LEGACY_ITEM_TABLE)
        sqlite.execSQL(
            "INSERT INTO item (id, type, title, body, sourceUrl, createdAt, updatedAt, lastSavedAt, " +
                "inTrash, trashedAt, trashChangedAt, reminderDate, expiryDate, timeHour, timeMinute) " +
                "VALUES ('kept', 'NOTE', 'Kept', 'body', NULL, 10, 15, 20, 0, NULL, NULL, NULL, NULL, NULL, NULL)",
        )
        sqlite.execSQL(
            "INSERT INTO item (id, type, title, body, sourceUrl, createdAt, updatedAt, lastSavedAt, " +
                "inTrash, trashedAt, trashChangedAt, reminderDate, expiryDate, timeHour, timeMinute) " +
                "VALUES ('past', 'LINK', 'Past', 'https://example.com/notes/1', 'https://example.com/notes/1', " +
                "NULL, NULL, NULL, 0, NULL, NULL, '2026-11-16', NULL, 9, 0)",
        )
        sqlite.version = 1
        sqlite.close()

        val migrationAt =
            LocalDate
                .of(2026, 11, 16)
                .atTime(12, 0)
                .toInstant(ZoneOffset.UTC)
                .toEpochMilli()
        val opened =
            Room
                .databaseBuilder(context, VaultDatabase::class.java, name)
                .addMigrations(fixedClockMigration(migrationAt))
                .allowMainThreadQueries()
                .build()
        val store = ItemStore(opened)
        val kept = store.item(ItemId("kept"))
        val past = store.item(ItemId("past"))
        opened.close()
        context.deleteDatabase(name)

        assertEquals("kept", kept?.id?.value)
        assertEquals(10L, kept?.createdAt)
        assertEquals(20L, kept?.lastSavedAt)
        assertEquals(migrationAt, kept?.updatedAt)
        assertNull(kept?.userEditedAt)
        assertFalse(kept?.inTrash ?: true)
        assertEquals(migrationAt, past?.createdAt)
        assertEquals(migrationAt, past?.lastSavedAt)
        assertTrue(past?.firedOccurrences?.contains("reminder:2026-11-16T09:00") ?: false)
        assertEquals("https://example.com/notes/1", past?.sourceUrl)
    }

    @Test
    fun aSavedItemRoundTripsAndATombstoneRetiresItsId() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val database = openInMemoryVault(context)
        val store = ItemStore(database)
        val ids = ItemIds(store.usedIds())
        val item =
            createItem(
                ids,
                ItemType.NOTE,
                "Kept",
                "body",
                now = 1_000L,
                draw = { "round" },
            )
        store.save(item)
        val trashed = moveToTrash(item, endedAt = 2_000L)
        store.save(trashed)
        val tombstone = tombstoneFor(trashed, writtenAt = 3_000L)
        store.saveTombstone(tombstone)

        val loaded = store.item(ItemId("round"))
        val storedTombstone = store.tombstone(ItemId("round"))
        val used = store.usedIds()
        database.close()

        assertEquals(item.id, loaded?.id)
        assertEquals(2_000L, loaded?.trashedAt)
        assertEquals(2_000L, loaded?.trashChangedAt)
        assertTrue(loaded?.inTrash ?: false)
        assertNull(loaded?.userEditedAt)
        assertEquals(tombstone, storedTombstone)
        assertTrue(used.contains("round"))
    }
}

private fun fixedClockMigration(migrationAt: Long) =
    object : androidx.room.migration.Migration(1, 2) {
        override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
            migrateLegacyDatabase(db, migrationAt, ZoneOffset.UTC)
        }
    }
