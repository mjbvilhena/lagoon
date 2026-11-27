package com.mjbvilhena.lagoon.domain.item

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ItemScreensTest {
    @Test
    fun homeIsNewestLastSavedAndHidesTrash() {
        val older = sample("older", lastSaved = 1_000L)
        val newer = sample("newer", lastSaved = 3_000L)
        val trashed = moveToTrash(sample("gone", lastSaved = 9_000L), endedAt = 9_500L)
        val rows = homeItems(listOf(older, trashed, newer))
        assertEquals(listOf("newer", "older"), rows.map { it.id.value })
    }

    @Test
    fun anEditDoesNotMoveLastSavedAndAnEmptyTitleIsRefused() {
        val item = sample("kept", lastSaved = 1_000L)
        val refused = editItem(item, title = "  ", body = "x", notes = emptyList(), tags = emptyList(), now = 5_000L)
        assertTrue(refused is EditResult.TitleRequired)
        val saved =
            editItem(
                item,
                title = "Kept",
                body = "changed",
                notes = addNote(emptyList(), "n1", "2026-11-21", "why"),
                tags = listOf("ferry"),
                now = 4_000L,
            ) as EditResult.Saved
        assertEquals(1_000L, saved.item.lastSavedAt)
        assertEquals(1_000L, saved.item.createdAt)
        assertEquals(saved.item.updatedAt, saved.item.userEditedAt)
        val editedNote = replaceNoteText(saved.item.notes, "n1", "still why")
        assertEquals("2026-11-21", editedNote.single().writtenOn)
        val withToday = addNote(editedNote, "n2", "2026-11-22", "more")
        assertEquals("2026-11-22", withToday.last().writtenOn)
    }

    @Test
    fun aLockedSensitiveDetailShowsNothing() {
        val item = sample("secret", lastSaved = 1_000L).copy(sensitive = true, title = "Hidden", body = "no")
        val locked = detailContent(item, sensitiveUnlocked = false)
        assertFalse(locked.shown)
        assertEquals("", locked.title)
        assertEquals("", locked.body)
        val open = detailContent(item.copy(sensitive = false), sensitiveUnlocked = false)
        assertTrue(open.shown)
        assertEquals("Hidden", open.title)
    }

    private fun sample(
        id: String,
        lastSaved: Long,
    ): Item = createItem(ItemIds(), ItemType.NOTE, "Title", "body", now = lastSaved, draw = { id })
}
