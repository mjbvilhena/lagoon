package com.mjbvilhena.lagoon

import android.app.Activity
import android.content.Intent
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import com.mjbvilhena.lagoon.ui.LauncherActivity
import com.mjbvilhena.lagoon.ui.PassphraseActivity
import com.mjbvilhena.lagoon.ui.SettingsActivity
import com.mjbvilhena.lagoon.ui.ShareTargetActivity
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import java.io.File

@RunWith(RobolectricTestRunner::class)
class PassphraseScreenTest {
    @Test
    @Config(sdk = [28], application = PassphraseVaultApplication::class)
    fun j9SetsAPassphraseAndRejectsAWeakOne() {
        val share = launchShare("https://example.com/pending-share")
        val setup = nextPassphrase(share)
        assertTrue(flags(setup))
        assertTrue(passwordField(setup))
        assertTrue(
            texts(setup).contains(
                "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key.",
            ),
        )
        assertFalse(texts(setup).any { it.contains("strength", ignoreCase = true) })
        type(setup, "short-pass")
        click(setup, "Accept")
        assertTrue(texts(setup).contains("Use 12 to 128 characters."))
        assertFalse(header().isFile)
        type(setup, "q1w2e3r4t5y6")
        click(setup, "Accept")
        assertTrue(texts(setup).contains("That passphrase is too common."))
        assertFalse(header().isFile)
        type(setup, "vault-fixture-phrase")
        click(setup, "Accept")
        assertTrue(texts(setup).contains("Saved to Lagoon"))
        assertTrue(header().isFile)
        assertFalse(filesContain("vault-fixture-phrase"))
        assertFalse(logContains("vault-fixture-phrase"))
        val settings = Robolectric.buildActivity(SettingsActivity::class.java).setup().get()
        assertTrue(
            texts(
                settings,
            ).contains("This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key."),
        )
        assertFalse(texts(settings).contains("Check my passphrase"))
        assertFalse(texts(settings).any { it.contains("Back up now") })
        assertFalse(secure(settings))
    }

    @Test
    @Config(sdk = [28], application = PassphraseVaultApplication::class)
    fun declineSaysNothingWasSavedAndWritesNoVault() {
        val share = launchShare("A pending note that must stay in memory.")
        val setup = nextPassphrase(share)
        click(setup, "Decline")
        assertTrue(texts(setup).contains("Nothing was saved."))
        assertFalse(header().isFile)
        assertFalse(filesContain("A pending note that must stay in memory."))
        assertFalse(filesContain("vault-fixture-phrase"))
        val app = ApplicationProvider.getApplicationContext<PassphraseVaultApplication>()
        assertTrue(app.vault.home().isEmpty())
    }

    @Test
    @Config(sdk = [28], application = LowMemoryVaultApplication::class)
    fun lowMemoryRefusesSetupWithoutLoweringTheFloor() {
        val setup = Robolectric.buildActivity(PassphraseActivity::class.java).setup().get()
        assertTrue(texts(setup).contains("This phone cannot allocate 65536 KiB, so a vault cannot be created."))
        assertFalse(texts(setup).contains("Accept"))
        assertFalse(header().isFile)
    }

    @Test
    @Config(sdk = [28], application = ReadyVaultApplication::class)
    fun settingsOnAHardwareKeyHasNoWarningAndNoPassphraseCheck() {
        val home = Robolectric.buildActivity(LauncherActivity::class.java).setup().get()
        assertFalse(secure(home))
        click(home, "Settings")
        val settings = nextSettings(home)
        assertFalse(
            texts(settings).contains(
                "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key.",
            ),
        )
        assertFalse(texts(settings).contains("Check my passphrase"))
        assertFalse(texts(settings).any { it.contains("Back up now") })
    }

    @Test
    @Config(sdk = [28], application = PassphraseVaultApplication::class)
    fun aWrongPassphraseChangesNothingAndTheForgottenOfferWritesNothing() {
        val setup = Robolectric.buildActivity(PassphraseActivity::class.java).setup().get()
        type(setup, "vault-fixture-phrase")
        click(setup, "Accept")
        header().parentFile?.resolve("wrap.bin")?.delete()
        val before = header().readBytes()
        val unlock = Robolectric.buildActivity(PassphraseActivity::class.java).setup().get()
        assertTrue(texts(unlock).contains("I've forgotten my passphrase"))
        type(unlock, "another-passphrase")
        click(unlock, "Accept")
        assertTrue(texts(unlock).contains("Wait, then try again."))
        assertTrue(header().readBytes().contentEquals(before))
        click(unlock, "I've forgotten my passphrase")
        assertTrue(texts(unlock).contains("The vault can't be opened on this phone without the passphrase."))
        click(unlock, "New empty vault")
        assertTrue(header().readBytes().contentEquals(before))
        assertFalse(filesContain("another-passphrase"))
    }

    private fun launchShare(text: String): ShareTargetActivity {
        val intent =
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }
        return Robolectric.buildActivity(ShareTargetActivity::class.java, intent).setup().get()
    }

    private fun nextPassphrase(activity: Activity): PassphraseActivity {
        val next =
            org.robolectric.Shadows
                .shadowOf(activity)
                .nextStartedActivity
        return Robolectric.buildActivity(PassphraseActivity::class.java, next).setup().get()
    }

    private fun nextSettings(activity: Activity): SettingsActivity {
        val next =
            org.robolectric.Shadows
                .shadowOf(activity)
                .nextStartedActivity
        return Robolectric.buildActivity(SettingsActivity::class.java, next).setup().get()
    }

    private fun header(): File {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        return File(context.filesDir, "vault-key/header.bin")
    }

    private fun filesContain(needle: String): Boolean {
        val root = ApplicationProvider.getApplicationContext<android.content.Context>().filesDir
        if (!root.exists()) {
            return false
        }
        return root.walkTopDown().filter { it.isFile }.any { file ->
            file.readBytes().toString(Charsets.UTF_8).contains(needle)
        }
    }

    private fun logContains(needle: String): Boolean =
        ShadowLog.getLogs().any { log ->
            (log.msg ?: "").contains(needle) || (log.tag ?: "").contains(needle)
        }

    private fun flags(activity: Activity): Boolean = activity.window.attributes.flags and WindowManager.LayoutParams.FLAG_SECURE != 0

    private fun secure(activity: Activity): Boolean = flags(activity)

    private fun passwordField(activity: Activity): Boolean {
        val field =
            activity.window.decorView
                .findAll<EditText>()
                .first()
        val password = field.inputType and InputType.TYPE_TEXT_VARIATION_PASSWORD
        val learning = field.imeOptions and EditorInfo.IME_FLAG_NO_PERSONALIZED_LEARNING
        return password == InputType.TYPE_TEXT_VARIATION_PASSWORD && learning != 0 && !field.isSaveEnabled
    }

    private fun type(
        activity: Activity,
        value: String,
    ) {
        activity.window.decorView
            .findAll<EditText>()
            .first()
            .setText(value)
    }

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
}
