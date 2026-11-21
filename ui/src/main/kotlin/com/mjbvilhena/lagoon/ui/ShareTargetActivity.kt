package com.mjbvilhena.lagoon.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import com.mjbvilhena.lagoon.domain.share.ShareInput
import java.time.Instant
import java.time.ZoneId

/** Share target. One tap stores a link or plain text, or says that nothing was saved. */
class ShareTargetActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message =
            (application as VaultHost).vault.acceptShare(
                shareInput(),
                Instant.now().toEpochMilli(),
                Instant
                    .now()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .toString(),
            )
        val text =
            TextView(this).apply {
                this.text = message
                contentDescription = message
                textSize = 20f
                setPadding(48, 48, 48, 48)
            }
        setContentView(text)
    }

    private fun shareInput(): ShareInput {
        val stream = streamUri()
        return ShareInput(
            mimeType = intent.type,
            text = intent.getStringExtra(Intent.EXTRA_TEXT),
            subject = intent.getStringExtra(Intent.EXTRA_SUBJECT),
            streamScheme = stream?.scheme,
            streamAuthority = stream?.authority,
            streamSizeBytes = stream?.let { streamSize(it) },
            sendingApp = callingPackage ?: referrer?.host,
            ownFileProviderAuthorities = setOf("$packageName.fileprovider"),
        )
    }

    @Suppress("DEPRECATION")
    private fun streamUri(): Uri? = intent.getParcelableExtra(Intent.EXTRA_STREAM)

    private fun streamSize(uri: Uri): Long? =
        try {
            contentResolver.openAssetFileDescriptor(uri, "r")?.use { descriptor ->
                val length = descriptor.length
                if (length >= 0) length else null
            }
        } catch (_: Exception) {
            null
        }
}
