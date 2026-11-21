package com.mjbvilhena.lagoon.domain.share

import com.mjbvilhena.lagoon.domain.item.ItemIds
import com.mjbvilhena.lagoon.domain.item.ItemType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ShareCaptureTest {
    @Test
    fun aLinkIsStoredAndConfirmedByTheCaller() {
        val saved = store(text = "https://example.com/notes/1") as ShareDecision.Store
        assertEquals(ItemType.LINK, saved.item.type)
        assertEquals("https://example.com/notes/1", saved.item.sourceUrl)
        assertEquals("example.com/notes/1", saved.item.title)
        assertEquals("example.com", saved.item.sourceDomain)
        assertEquals(1_000L, saved.item.createdAt)
        assertEquals(1_000L, saved.item.lastSavedAt)
        assertTrue(saved.item.notes.isEmpty())
    }

    @Test
    fun textBesideAUrlBecomesOnePersonalNote() {
        val saved =
            store(
                text = "See https://example.com/notes/1 tomorrow",
                subject = "Tide",
            ) as ShareDecision.Store
        assertEquals("https://example.com/notes/1", saved.item.sourceUrl)
        assertEquals("Tide", saved.item.title)
        assertEquals(1, saved.item.notes.size)
        assertEquals(
            "2026-11-21",
            saved.item.notes
                .single()
                .writtenOn,
        )
        assertEquals(
            "See tomorrow",
            saved.item.notes
                .single()
                .text,
        )
    }

    @Test
    fun aTokenLookingLinkUsesTheHostOnly() {
        val saved =
            store(text = "https://example.com/reset?access_token=abc") as ShareDecision.Store
        assertEquals("example.com", saved.item.title)
        assertTrue(isTokenLooking("https://example.com/reset?access_token=abc"))
        assertTrue(!isTokenLooking("https://example.com/notes/1"))
        assertTrue(!isTokenLooking("https://example.com/how-to-make-sourdough-bread-at-home"))
        assertTrue(!isTokenLooking("https://example.com/how-to-fix-a-leaking-tap-2026"))
    }

    @Test
    fun plainTextBecomesANoteAndALongLineIsCutInTheTitleOnly() {
        val text = "a".repeat(130) + "\nrest"
        val saved = store(text = text, mimeType = "text/plain") as ShareDecision.Store
        assertEquals(ItemType.NOTE, saved.item.type)
        assertEquals(120, saved.item.title.length)
        assertEquals(text, saved.item.body)
    }

    @Test
    fun aSubjectIsTheTitle() {
        val saved = store(text = "Spring tide", subject = "Sunday") as ShareDecision.Store
        assertEquals("Sunday", saved.item.title)
        assertEquals("Spring tide", saved.item.body)
    }

    @Test
    fun whitespaceFileUrisAndALargeImageStoreNothing() {
        assertTrue(store(text = "  \n") is ShareDecision.Refuse)
        assertTrue(store(text = "https://example.com", scheme = "file") is ShareDecision.Refuse)
        assertTrue(
            store(
                text = "https://example.com",
                scheme = "content",
                authority = "com.mjbvilhena.lagoon.fileprovider",
            ) is ShareDecision.Refuse,
        )
        assertTrue(
            store(
                mimeType = "image/jpeg",
                size = IMAGE_BYTE_CAP + 1,
            ) is ShareDecision.Refuse,
        )
        assertNull((store(text = "https://example.com/a") as ShareDecision.Store).item.sendingApp)
    }

    @Test
    fun theSendingAppIsKeptWhenThePlatformReportsIt() {
        val saved = store(text = "https://example.com/a", sendingApp = "Messages") as ShareDecision.Store
        assertEquals("Messages", saved.item.sendingApp)
    }

    private fun store(
        text: String? = null,
        subject: String? = null,
        mimeType: String? = "text/plain",
        scheme: String? = null,
        authority: String? = null,
        size: Long? = null,
        sendingApp: String? = null,
    ): ShareDecision {
        val draws = ArrayDeque(listOf("note", "item"))
        return decideShare(
            ShareInput(
                mimeType = mimeType,
                text = text,
                subject = subject,
                streamScheme = scheme,
                streamAuthority = authority,
                streamSizeBytes = size,
                sendingApp = sendingApp,
                ownFileProviderAuthorities = setOf("com.mjbvilhena.lagoon.fileprovider"),
            ),
            ItemIds(),
            now = 1_000L,
            today = "2026-11-21",
            draw = { draws.removeFirst() },
        )
    }
}
