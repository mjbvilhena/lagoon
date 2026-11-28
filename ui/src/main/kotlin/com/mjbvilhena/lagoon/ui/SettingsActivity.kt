package com.mjbvilhena.lagoon.ui

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.mjbvilhena.lagoon.domain.vault.HARDWARE_WARNING
import com.mjbvilhena.lagoon.domain.vault.SETTINGS

/** The hardware warning stays here. It is not a second accept gate. */
class SettingsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val column =
            LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
            }
        column.addView(label(SETTINGS))
        if ((application as VaultHost).vault.settingsHardwareWarning()) {
            column.addView(label(HARDWARE_WARNING))
        }
        setContentView(ScrollView(this).apply { addView(column) })
    }

    private fun label(value: String): TextView =
        TextView(this).apply {
            text = value
            contentDescription = value
        }
}
