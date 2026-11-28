package com.mjbvilhena.lagoon.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.mjbvilhena.lagoon.domain.vault.ACCEPT
import com.mjbvilhena.lagoon.domain.vault.DECLINE
import com.mjbvilhena.lagoon.domain.vault.FORGOTTEN_PASSPHRASE
import com.mjbvilhena.lagoon.domain.vault.FORGOTTEN_SENTENCE
import com.mjbvilhena.lagoon.domain.vault.HARDWARE_WARNING
import com.mjbvilhena.lagoon.domain.vault.MEMORY_FLOOR
import com.mjbvilhena.lagoon.domain.vault.NEW_EMPTY_VAULT
import com.mjbvilhena.lagoon.domain.vault.PASSPHRASE_FIELD
import com.mjbvilhena.lagoon.domain.vault.PASSPHRASE_GUIDANCE
import com.mjbvilhena.lagoon.domain.vault.PASSPHRASE_LOSS
import com.mjbvilhena.lagoon.domain.vault.SET_PASSPHRASE
import com.mjbvilhena.lagoon.domain.vault.VaultPhase

/** Passphrase setup, key re-entry, and the local forgotten-passphrase offer. */
class PassphraseActivity : Activity() {
    private var declined = false
    private var forgotten = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        vault().preparePassphrase()
        show()
    }

    private fun show() {
        val column = column()
        column.addView(title(SET_PASSPHRASE))
        when {
            declined -> declined(column)
            forgotten -> forgotten(column)
            vault().memoryRefused() -> memory(column)
            vault().passphrasePhase() == VaultPhase.KEY_GONE -> unlock(column)
            else -> setup(column)
        }
        setContentView(scroll(column))
    }

    private fun setup(column: LinearLayout) {
        if (vault().setupHardwareWarning()) {
            column.addView(text(HARDWARE_WARNING))
        }
        column.addView(text(PASSPHRASE_GUIDANCE))
        column.addView(text(PASSPHRASE_LOSS))
        val field = passphraseField()
        val error = text("")
        column.addView(field)
        column.addView(error)
        column.addView(button(ACCEPT) { acceptSetup(field, error) })
        column.addView(button(DECLINE) { decline() })
    }

    private fun unlock(column: LinearLayout) {
        val field = passphraseField()
        val error = text("")
        column.addView(field)
        column.addView(error)
        column.addView(button(ACCEPT) { unlock(field, error) })
        column.addView(
            button(FORGOTTEN_PASSPHRASE) {
                forgotten = true
                show()
            },
        )
    }

    private fun forgotten(column: LinearLayout) {
        column.addView(text(FORGOTTEN_SENTENCE))
        column.addView(
            button(NEW_EMPTY_VAULT) {
                vault().newEmptyVault()
                show()
            },
        )
    }

    private fun memory(column: LinearLayout) {
        column.addView(text(MEMORY_FLOOR))
        column.addView(button(DECLINE) { decline() })
    }

    private fun declined(column: LinearLayout) {
        column.addView(text(declineMessage))
        column.addView(
            button(SET_PASSPHRASE) {
                declined = false
                vault().preparePassphrase()
                show()
            },
        )
    }

    private var declineMessage = ""

    private fun acceptSetup(
        field: EditText,
        error: TextView,
    ) {
        val step = vault().acceptPassphrase(field.text.toString())
        field.text.clear()
        if (step.vaultReady) {
            openNext(step.message)
        } else {
            error.text = step.message
            error.contentDescription = step.message
        }
    }

    private fun unlock(
        field: EditText,
        error: TextView,
    ) {
        val step = vault().unlockPassphrase(field.text.toString())
        field.text.clear()
        if (step.vaultReady) {
            openNext("")
        } else {
            error.text = step.message
            error.contentDescription = step.message
        }
    }

    private fun decline() {
        declineMessage = vault().declinePassphrase()
        declined = true
        show()
    }

    private fun openNext(message: String) {
        if (message.isEmpty()) {
            startActivity(Intent(this, LauncherActivity::class.java))
            finish()
            return
        }
        val column = column()
        column.addView(text(message))
        setContentView(scroll(column))
    }

    private fun passphraseField(): EditText =
        EditText(this).apply {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            imeOptions = imeOptions or EditorInfo.IME_FLAG_NO_PERSONALIZED_LEARNING
            contentDescription = PASSPHRASE_FIELD
            isSaveEnabled = false
            hint = PASSPHRASE_FIELD
        }

    private fun vault(): VaultSession = (application as VaultHost).vault

    private fun scroll(child: View): ScrollView = ScrollView(this).apply { addView(child) }

    private fun column(): LinearLayout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }

    private fun title(value: String): TextView = text(value)

    private fun text(value: String): TextView =
        TextView(this).apply {
            text = value
            contentDescription = value
        }

    private fun button(
        name: String,
        onClick: () -> Unit,
    ): Button =
        Button(this).apply {
            text = name
            contentDescription = name
            setOnClickListener { onClick() }
        }
}
