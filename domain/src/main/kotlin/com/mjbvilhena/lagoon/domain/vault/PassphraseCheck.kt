package com.mjbvilhena.lagoon.domain.vault

import java.text.Normalizer

const val PASSPHRASE_MIN = 12

const val PASSPHRASE_MAX = 128

sealed class PassphraseCheck {
    data class Accepted(
        val nfc: String,
    ) : PassphraseCheck()

    data object Length : PassphraseCheck()

    data object Common : PassphraseCheck()
}

fun interface CommonPasswordList {
    fun blocks(nfc: String): Boolean
}

/** NFC, then the length, then the list. The passphrase is not trimmed. There is no strength meter. */
fun checkPassphrase(
    raw: String,
    passwords: CommonPasswordList,
): PassphraseCheck {
    val nfc = Normalizer.normalize(raw, Normalizer.Form.NFC)
    val count = nfc.codePointCount(0, nfc.length)
    if (count < PASSPHRASE_MIN || count > PASSPHRASE_MAX) {
        return PassphraseCheck.Length
    }
    if (passwords.blocks(nfc)) {
        return PassphraseCheck.Common
    }
    return PassphraseCheck.Accepted(nfc)
}

fun blocksListedPassword(
    nfc: String,
    exact: Set<String>,
    folded: Set<String>,
): Boolean = nfc in exact || UnicodeCaseFold.fold(nfc) in folded
