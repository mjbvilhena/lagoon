package com.mjbvilhena.lagoon

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.mjbvilhena.lagoon.ui.LauncherActivity
import com.mjbvilhena.lagoon.ui.ShareTargetActivity
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class CoreLoopScreenTest {
    @Test
    fun aSharedLinkIsSavedAndListedNewestFirst() {
        val share = launchShare("https://example.com/notes/1", "text/plain")
        assertTrue(texts(share).contains("Saved to Lagoon"))

        val home = launchHome()
        assertTrue(texts(home).any { it.contains("example.com/notes/1") })
        assertTrue(texts(home).any { it.contains("Link") })
    }

    @Test
    fun whitespaceAndAFileUriSaveNothing() {
        val blank = launchShare(" \n\t", "text/plain")
        assertTrue(texts(blank).contains("Nothing was saved."))
        val file = launchShare("https://example.com/secret", "text/plain", "file:///tmp/secret")
        assertTrue(texts(file).contains("Nothing was saved."))
        val home = launchHome()
        assertTrue(texts(home).contains("Nothing is saved yet."))
    }

    @Test
    fun anEmptyTitleIsRefusedAndDeleteOffersUndo() {
        launchShare("Spring tide is on Sunday.", "text/plain")
        val home = launchHome()
        click(home, "Spring tide is on Sunday.")
        click(home, "Edit")
        val title = home.window.decorView.findViewByType<EditText>()
        title.setText("")
        click(home, "Save")
        assertTrue(texts(home).contains("A title is required."))

        val again = launchHome()
        click(again, "Spring tide is on Sunday.")
        click(again, "Delete")
        assertTrue(texts(again).contains("In trash"))
        assertTrue(texts(again).contains("Undo"))
        assertFalse(texts(again).any { it.contains("Spring tide is on Sunday.") })
        click(again, "Undo")
        assertTrue(texts(again).any { it.contains("Spring tide is on Sunday.") })
    }

    @Test
    fun theUndoWindowMovesTheItemOutOfTheHomeList() {
        launchShare("Spring tide is on Sunday.", "text/plain")
        val home = launchHome()
        click(home, "Spring tide is on Sunday.")
        click(home, "Delete")
        ShadowLooper.shadowMainLooper().idleFor(java.time.Duration.ofSeconds(8))
        assertTrue(texts(home).contains("Nothing is saved yet."))
    }

    private fun launchShare(
        text: String,
        mime: String,
        stream: String? = null,
    ): ShareTargetActivity {
        val intent =
            Intent(Intent.ACTION_SEND).apply {
                type = mime
                putExtra(Intent.EXTRA_TEXT, text)
                if (stream != null) {
                    putExtra(Intent.EXTRA_STREAM, android.net.Uri.parse(stream))
                }
            }
        return Robolectric.buildActivity(ShareTargetActivity::class.java, intent).setup().get()
    }

    private fun launchHome(): LauncherActivity = Robolectric.buildActivity(LauncherActivity::class.java).setup().get()

    private fun click(
        activity: Activity,
        label: String,
    ) {
        val button =
            activity.window.decorView.findAll<Button>().first { view ->
                view.text.contains(label) || view.contentDescription == label
            }
        button.performClick()
    }

    private fun texts(activity: Activity): List<String> =
        activity.window.decorView
            .findAll<TextView>()
            .map { it.text.toString() }

    private inline fun <reified T : View> View.findAll(): List<T> {
        val found = ArrayList<T>()
        val pending = ArrayDeque<View>()
        pending.add(this)
        while (pending.isNotEmpty()) {
            val view = pending.removeFirst()
            if (view is T) {
                found.add(view)
            }
            if (view is ViewGroup) {
                for (index in 0 until view.childCount) {
                    pending.add(view.getChildAt(index))
                }
            }
        }
        return found
    }

    private inline fun <reified T : View> View.findViewByType(): T = findAll<T>().first()
}
