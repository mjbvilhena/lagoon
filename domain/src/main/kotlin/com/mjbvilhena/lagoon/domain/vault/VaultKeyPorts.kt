package com.mjbvilhena.lagoon.domain.vault

fun interface Clock {
    fun nowMillis(): Long
}

fun interface SaltSource {
    fun salt(): ByteArray
}

sealed class DeriveOutcome {
    class Key(
        val bytes: ByteArray,
    ) : DeriveOutcome()

    data object OutOfBounds : DeriveOutcome()

    data object AllocationFailed : DeriveOutcome()
}

fun interface RootKeyDeriver {
    fun derive(
        passphrase: ByteArray,
        header: VaultHeader,
    ): DeriveOutcome
}

class WrapInfo(
    val hardwareBacked: Boolean,
    val userAuthenticationRequired: Boolean,
    val unlockedDeviceRequired: Boolean,
    val invalidatedByBiometricEnrollment: Boolean,
)

interface KeyWrapper {
    fun ensureWrappingKey(): WrapInfo

    fun current(): WrapInfo?

    fun wrap(root: ByteArray): ByteArray

    fun unwrap(blob: ByteArray): ByteArray?

    fun deleteWrappingKey()

    fun present(): Boolean
}

interface KeyCheck {
    fun seal(
        root: ByteArray,
        aad: ByteArray,
    ): ByteArray

    fun opens(
        root: ByteArray,
        sealed: ByteArray,
        aad: ByteArray,
    ): Boolean
}

class PersistedVaultKey(
    val header: VaultHeader,
    val wrap: ByteArray,
    val verifier: ByteArray,
)

interface VaultKeyStore {
    fun read(): PersistedVaultKey?

    fun write(record: PersistedVaultKey)

    fun readRetry(): RetryState

    fun writeRetry(state: RetryState)

    fun storedBytes(): List<ByteArray>
}
