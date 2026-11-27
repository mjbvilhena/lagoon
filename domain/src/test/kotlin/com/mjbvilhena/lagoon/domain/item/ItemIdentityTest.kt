package com.mjbvilhena.lagoon.domain.item

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.ZoneOffset

class ItemIdentityTest {
    @Test
    fun anEditKeepsTheIdAndMovesBothClocksTogether() {
        val ids = ItemIds()
        val item = createItem(ids, ItemType.NOTE, "Kept", "body", now = 1_000L)
        val edited =
            applyUserEdit(
                item,
                UserFieldEdit(
                    title = "Kept again",
                    body = "new body",
                    notes = listOf(PersonalNote("n1", "2026-11-16", "why")),
                    tags = listOf("ferry"),
                    reminder = LocalSchedule("2026-12-01", 9, 0),
                    expiry = null,
                    attachments = listOf(AttachmentRef("a1", "page.pdf")),
                    sensitive = true,
                ),
                now = 1_500L,
            )

        assertEquals(item.id, edited.id)
        assertEquals(1_000L, edited.createdAt)
        assertEquals(1_000L, edited.lastSavedAt)
        assertEquals(1_500L, edited.updatedAt)
        assertEquals(1_500L, edited.userEditedAt)
        assertEquals("Kept again", edited.title)
        assertTrue(edited.sensitive)
    }

    @Test
    fun aClockSetBackDoesNotMoveUpdatedAtBackwards() {
        val ids = ItemIds()
        val item = createItem(ids, ItemType.NOTE, "Kept", "body", now = 5_000L)
        val edited =
            applyUserEdit(
                item,
                UserFieldEdit("Kept", "body", emptyList(), emptyList(), null, null, emptyList(), false),
                now = 1_000L,
            )

        assertEquals(5_001L, edited.updatedAt)
        assertEquals(edited.updatedAt, edited.userEditedAt)
    }

    @Test
    fun aSystemWriteSetsUpdatedAtAndLeavesUserEditedAt() {
        val ids = ItemIds()
        val item =
            applyUserEdit(
                createItem(ids, ItemType.NOTE, "Kept", "body", now = 1_000L),
                UserFieldEdit("Kept", "body", emptyList(), emptyList(), null, null, emptyList(), false),
                now = 2_000L,
            )
        val written = applySystemWrite(item, now = 3_000L) { it.copy(ocrText = "seen") }

        assertEquals(item.id, written.id)
        assertEquals(3_000L, written.updatedAt)
        assertEquals(2_000L, written.userEditedAt)
        assertEquals("seen", written.ocrText)
        assertEquals(item.createdAt, written.createdAt)
        assertEquals(item.lastSavedAt, written.lastSavedAt)
    }

    @Test
    fun aSystemWriteDoesNotClearTrashOrMoveLastSaved() {
        val ids = ItemIds()
        val trashed = moveToTrash(createItem(ids, ItemType.NOTE, "Kept", "body", now = 1_000L), endedAt = 2_000L)
        val written =
            applySystemWrite(trashed, now = 3_000L) {
                it.copy(inTrash = false, trashedAt = null, trashChangedAt = null, lastSavedAt = 9_000L)
            }

        assertTrue(written.inTrash)
        assertEquals(2_000L, written.trashedAt)
        assertEquals(2_000L, written.trashChangedAt)
        assertEquals(trashed.lastSavedAt, written.lastSavedAt)
        assertNull(written.userEditedAt)
    }

    @Test
    fun lastSavedMovesOnARepeatShareAndNotOnAnEdit() {
        val ids = ItemIds()
        val item = createItem(ids, ItemType.LINK, "Host", "body", now = 1_000L)
        val repeated = markRepeatShare(item, now = 4_000L)
        val edited =
            applyUserEdit(
                repeated,
                UserFieldEdit("Host", "body", emptyList(), emptyList(), null, null, emptyList(), false),
                now = 8_000L,
            )

        assertEquals(4_000L, repeated.lastSavedAt)
        assertEquals(4_000L, edited.lastSavedAt)
        assertNull(repeated.userEditedAt)
        assertEquals(mergeLastSaved(1_000L, 4_000L), 4_000L)
    }

    @Test
    fun idsDifferAcrossInstallsAndATombstoneIdIsNotReused() {
        val left =
            ItemIds().allocate {
                java.util.UUID
                    .randomUUID()
                    .toString()
            }
        val right =
            ItemIds().allocate {
                java.util.UUID
                    .randomUUID()
                    .toString()
            }
        assertFalse(left.value == right.value)

        val ids = ItemIds()
        val item = createItem(ids, ItemType.NOTE, "Kept", "body", now = 1_000L, draw = { "fresh" })
        val tombstone = tombstoneFor(moveToTrash(item, endedAt = 2_000L), writtenAt = 3_000L)
        ids.remember(tombstone.itemId)
        val draws = ArrayDeque(listOf(tombstone.itemId.value, "after"))
        val next = ids.allocate { draws.removeFirst() }

        assertEquals("after", next.value)
        assertEquals(item.id, tombstone.itemId)
        assertEquals(2_000L, tombstone.trashedAt)
        assertEquals(2_000L, tombstone.trashChangedAt)
        assertEquals(3_000L, tombstone.writtenAt)
    }

    @Test
    fun copyingARecordToAnotherInstallKeepsTheId() {
        val ids = ItemIds()
        val item = createItem(ids, ItemType.NOTE, "Kept", "body", now = 1_000L, draw = { "stable" })
        val other = ItemIds()
        other.remember(item.id)
        val stored = item.copy()

        assertEquals("stable", stored.id.value)
        assertTrue(other.used().contains(stored.id.value))
    }

    @Test
    fun firedStateStaysFiredPerOccurrenceAndALaterReminderDoesNot() {
        val earlier = "reminder:2026-11-01T09:00"
        val later = "reminder:2026-12-01T09:00"
        val merged = mergeFiredOccurrences(setOf(earlier), emptySet())

        assertTrue(merged.contains(earlier))
        assertFalse(merged.contains(later))
        assertEquals(setOf(earlier), mergeFiredOccurrences(merged, setOf(earlier)))
    }

    @Test
    fun migrationFillsMissingFieldsAndMarksAPastOccurrenceFired() {
        val zone = ZoneOffset.UTC
        val migrationAt = utc(2026, 11, 16, 12, 0)
        val kept =
            migrateLegacyItem(
                legacy(id = "kept", createdAt = 10L, lastSavedAt = 20L, updatedAt = 15L),
                migrationAt,
                zone,
            )
        val missing =
            migrateLegacyItem(
                legacy(
                    id = "missing",
                    reminderDate = "2026-11-16",
                    timeHour = 9,
                    timeMinute = 0,
                ),
                migrationAt,
                zone,
            )
        val future =
            migrateLegacyItem(
                legacy(id = "future", expiryDate = "2026-12-01"),
                migrationAt,
                zone,
            )
        val trashed =
            migrateLegacyItem(
                legacy(id = "trashed", inTrash = true, trashedAt = 4L, trashChangedAt = 4L, updatedAt = 9_000L),
                migrationAt = 5_000L,
                zone,
            )

        assertEquals(10L, kept.createdAt)
        assertEquals(20L, kept.lastSavedAt)
        assertEquals(migrationAt, kept.updatedAt)
        assertNull(kept.userEditedAt)
        assertFalse(kept.inTrash)
        assertNull(kept.trashedAt)

        assertEquals(migrationAt, missing.createdAt)
        assertEquals(migrationAt, missing.lastSavedAt)
        assertNull(missing.userEditedAt)
        assertEquals(setOf(reminderOccurrenceKey("2026-11-16", 9, 0)), missing.firedOccurrences)

        assertTrue(future.firedOccurrences.isEmpty())
        val pastExpiry =
            migrateLegacyItem(
                legacy(id = "past-expiry", expiryDate = "2026-11-01"),
                migrationAt,
                zone,
            )
        assertEquals(
            expiryNoticeKeys("2026-11-01", DEFAULT_SCHEDULE_HOUR, DEFAULT_SCHEDULE_MINUTE).toSet(),
            pastExpiry.firedOccurrences,
        )

        assertTrue(trashed.inTrash)
        assertEquals(4L, trashed.trashedAt)
        assertEquals(4L, trashed.trashChangedAt)
        assertEquals(9_001L, trashed.updatedAt)
        assertNull(trashed.userEditedAt)
    }

    private fun legacy(
        id: String,
        createdAt: Long? = null,
        updatedAt: Long? = null,
        lastSavedAt: Long? = null,
        inTrash: Boolean = false,
        trashedAt: Long? = null,
        trashChangedAt: Long? = null,
        reminderDate: String? = null,
        expiryDate: String? = null,
        timeHour: Int? = null,
        timeMinute: Int? = null,
    ): LegacyItemRow =
        LegacyItemRow(
            id = id,
            type = "NOTE",
            title = "Kept",
            body = "body",
            sourceUrl = null,
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSavedAt = lastSavedAt,
            inTrash = inTrash,
            trashedAt = trashedAt,
            trashChangedAt = trashChangedAt,
            reminderDate = reminderDate,
            expiryDate = expiryDate,
            timeHour = timeHour,
            timeMinute = timeMinute,
        )

    private fun utc(
        year: Int,
        month: Int,
        day: Int,
        hour: Int,
        minute: Int,
    ): Long =
        java.time.LocalDate
            .of(year, month, day)
            .atTime(hour, minute)
            .toInstant(ZoneOffset.UTC)
            .toEpochMilli()
}
