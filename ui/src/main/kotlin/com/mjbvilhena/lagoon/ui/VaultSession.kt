package com.mjbvilhena.lagoon.ui

import com.mjbvilhena.lagoon.domain.item.DetailContent
import com.mjbvilhena.lagoon.domain.item.EditResult
import com.mjbvilhena.lagoon.domain.item.Item
import com.mjbvilhena.lagoon.domain.item.PersonalNote
import com.mjbvilhena.lagoon.domain.share.ShareInput
import com.mjbvilhena.lagoon.domain.vault.VaultPhase

interface VaultHost {
    val vault: VaultSession
}

interface VaultSession {
    fun acceptShare(
        input: ShareInput,
        now: Long,
        today: String,
    ): String

    fun home(): List<Item>

    fun detail(id: String): DetailContent?

    fun edit(
        id: String,
        title: String,
        body: String,
        notes: List<PersonalNote>,
        tags: List<String>,
        now: Long,
    ): EditResult

    fun item(id: String): Item?

    fun beginDelete(id: String)

    fun undoDelete(id: String)

    fun confirmDelete(now: Long)

    fun passphrasePhase(): VaultPhase

    fun preparePassphrase()

    fun setupHardwareWarning(): Boolean

    fun settingsHardwareWarning(): Boolean

    fun memoryRefused(): Boolean

    fun acceptPassphrase(passphrase: String): PassphraseStep

    fun unlockPassphrase(passphrase: String): PassphraseStep

    fun declinePassphrase(): String

    fun newEmptyVault(): String
}

const val PASSPHRASE_REQUIRED = "PASSPHRASE_REQUIRED"

class PassphraseStep(
    val message: String,
    val vaultReady: Boolean,
)
