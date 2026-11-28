package com.mjbvilhena.lagoon.domain.vault

const val SET_PASSPHRASE = "Set a passphrase"

const val PASSPHRASE_FIELD = "Passphrase"

const val ACCEPT = "Accept"

const val DECLINE = "Decline"

const val SETTINGS = "Settings"

const val PASSPHRASE_GUIDANCE =
    "Choose a passphrase of 12 to 128 characters that you do not use anywhere else."

const val PASSPHRASE_LOSS =
    "The vault key is derived from this passphrase. Forgetting it and losing this device means the content is lost. " +
        "Uninstall or clear storage destroys the Keystore copy. " +
        "Losing the signing key forces a reinstall that destroys that copy too."

const val HARDWARE_WARNING =
    "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key."

const val PASSPHRASE_LENGTH = "Use 12 to 128 characters."

const val PASSPHRASE_COMMON = "That passphrase is too common."

const val PASSPHRASE_WAIT = "Wait, then try again."

const val NO_VAULT_CREATED = "No vault was created."

const val MEMORY_FLOOR =
    "This phone cannot allocate 65536 KiB, so a vault cannot be created."

const val FORGOTTEN_PASSPHRASE = "I've forgotten my passphrase"

const val FORGOTTEN_SENTENCE = "The vault can't be opened on this phone without the passphrase."

const val NEW_EMPTY_VAULT = "New empty vault"

/** Twelve code points. A benchmark fixture, not a person's passphrase. */
const val FIXTURE_PASSPHRASE = "fixture-pass"
