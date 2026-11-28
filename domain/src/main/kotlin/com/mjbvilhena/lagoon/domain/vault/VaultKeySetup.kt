package com.mjbvilhena.lagoon.domain.vault

import java.nio.charset.StandardCharsets
import java.util.Arrays

enum class VaultPhase { SETUP, KEY_GONE, READY }

sealed class VaultOutcome {
    data object Created : VaultOutcome()

    data object Recreated : VaultOutcome()

    data class Refused(
        val reason: RefuseReason,
    ) : VaultOutcome()

    data class Wrong(
        val waitSeconds: Int,
    ) : VaultOutcome()

    data class Waiting(
        val secondsRemaining: Long,
    ) : VaultOutcome()

    data object Unchanged : VaultOutcome()
}

enum class RefuseReason { LENGTH, COMMON, MEMORY, HARDWARE, DAMAGED }

class VaultKeySetup(
    private val deriver: RootKeyDeriver,
    private val wrapper: KeyWrapper,
    private val check: KeyCheck,
    private val store: VaultKeyStore,
    private val passwords: CommonPasswordList,
    private val clock: Clock,
    private val salts: SaltSource,
) {
    fun phase(): VaultPhase {
        val record = store.read() ?: return VaultPhase.SETUP
        if (!record.header.withinBounds()) {
            return VaultPhase.KEY_GONE
        }
        if (!wrapper.present()) {
            return VaultPhase.KEY_GONE
        }
        val root = wrapper.unwrap(record.wrap) ?: return VaultPhase.KEY_GONE
        root.wipe()
        return VaultPhase.READY
    }

    fun showHardwareWarning(): Boolean {
        if (phase() != VaultPhase.READY) {
            return false
        }
        return softwareWrap()
    }

    fun setupHardwareWarning(): Boolean = phase() == VaultPhase.SETUP && softwareWrap()

    fun probeWrappingKey() {
        if (store.read() == null) {
            wrapper.ensureWrappingKey()
        }
    }

    private fun softwareWrap(): Boolean {
        val info = wrapper.current() ?: return false
        return !info.hardwareBacked
    }

    fun allocationAvailable(): Boolean {
        val header = VaultHeader.floor(ByteArray(SALT_BYTES))
        val outcome = deriver.derive(fixtureBytes(), header)
        if (outcome is DeriveOutcome.Key) {
            outcome.bytes.wipe()
        }
        return outcome !is DeriveOutcome.AllocationFailed
    }

    fun acceptNew(
        passphrase: String,
        hardwareAccepted: Boolean,
    ): VaultOutcome {
        if (store.read() != null) {
            return VaultOutcome.Unchanged
        }
        when (val checked = checkPassphrase(passphrase, passwords)) {
            PassphraseCheck.Length -> return VaultOutcome.Refused(RefuseReason.LENGTH)
            PassphraseCheck.Common -> return VaultOutcome.Refused(RefuseReason.COMMON)
            is PassphraseCheck.Accepted -> {
                if (!allocationAvailable()) {
                    deleteOrphanWrap()
                    return VaultOutcome.Refused(RefuseReason.MEMORY)
                }
                val info = wrapper.ensureWrappingKey()
                if (!info.hardwareBacked && !hardwareAccepted) {
                    wrapper.deleteWrappingKey()
                    return VaultOutcome.Refused(RefuseReason.HARDWARE)
                }
                val header = VaultHeader.floor(salts.salt())
                val passBytes = checked.nfc.toByteArray(StandardCharsets.UTF_8)
                try {
                    return when (val derived = deriver.derive(passBytes, header)) {
                        DeriveOutcome.AllocationFailed -> {
                            wrapper.deleteWrappingKey()
                            VaultOutcome.Refused(RefuseReason.MEMORY)
                        }
                        DeriveOutcome.OutOfBounds -> {
                            wrapper.deleteWrappingKey()
                            VaultOutcome.Refused(RefuseReason.DAMAGED)
                        }
                        is DeriveOutcome.Key ->
                            try {
                                writeNew(header, derived.bytes)
                                store.writeRetry(RetryState.CLEAR)
                                VaultOutcome.Created
                            } finally {
                                derived.bytes.wipe()
                            }
                    }
                } finally {
                    passBytes.wipe()
                }
            }
        }
    }

    fun decline() {
        if (store.read() == null) {
            wrapper.deleteWrappingKey()
        }
    }

    fun unlock(passphrase: String): VaultOutcome {
        val record = store.read() ?: return VaultOutcome.Refused(RefuseReason.DAMAGED)
        if (!record.header.withinBounds()) {
            return VaultOutcome.Refused(RefuseReason.DAMAGED)
        }
        val now = clock.nowMillis()
        val retry = store.readRetry()
        val remaining = retry.secondsRemaining(now)
        if (remaining > 0) {
            return VaultOutcome.Waiting(remaining)
        }
        when (val checked = checkPassphrase(passphrase, passwords)) {
            PassphraseCheck.Length -> return VaultOutcome.Refused(RefuseReason.LENGTH)
            PassphraseCheck.Common -> return VaultOutcome.Refused(RefuseReason.COMMON)
            is PassphraseCheck.Accepted -> {
                val passBytes = checked.nfc.toByteArray(StandardCharsets.UTF_8)
                try {
                    return when (val derived = deriver.derive(passBytes, record.header)) {
                        DeriveOutcome.AllocationFailed -> VaultOutcome.Refused(RefuseReason.MEMORY)
                        DeriveOutcome.OutOfBounds -> VaultOutcome.Refused(RefuseReason.DAMAGED)
                        is DeriveOutcome.Key ->
                            finishUnlock(record, derived.bytes, now)
                    }
                } finally {
                    passBytes.wipe()
                }
            }
        }
    }

    /** The local offer is shown. Drive confirmation is not this slice. Nothing is written. */
    fun newEmptyVault(): VaultOutcome = VaultOutcome.Unchanged

    private fun finishUnlock(
        record: PersistedVaultKey,
        root: ByteArray,
        now: Long,
    ): VaultOutcome {
        val aad = record.header.encode()
        val opens = check.opens(root, record.verifier, aad)
        if (!opens) {
            root.wipe()
            val next = store.readRetry().afterWrong(now)
            store.writeRetry(next)
            return VaultOutcome.Wrong(RetryState.waitSeconds(next.wrongCount - 1))
        }
        val existing = if (wrapper.present()) wrapper.unwrap(record.wrap) else null
        if (existing == null) {
            wrapper.ensureWrappingKey()
            val wrap = wrapper.wrap(root)
            store.write(PersistedVaultKey(record.header, wrap, record.verifier))
        } else {
            existing.wipe()
        }
        root.wipe()
        store.writeRetry(RetryState.CLEAR)
        return VaultOutcome.Recreated
    }

    private fun writeNew(
        header: VaultHeader,
        root: ByteArray,
    ) {
        val aad = header.encode()
        val verifier = check.seal(root, aad)
        val wrap = wrapper.wrap(root)
        store.write(PersistedVaultKey(header, wrap, verifier))
    }

    private fun deleteOrphanWrap() {
        if (store.read() == null) {
            wrapper.deleteWrappingKey()
        }
    }

    private fun fixtureBytes(): ByteArray = FIXTURE_PASSPHRASE.toByteArray(StandardCharsets.UTF_8)
}

fun ByteArray.wipe() {
    Arrays.fill(this, 0.toByte())
}

fun outcomeMessage(outcome: VaultOutcome): String =
    when (outcome) {
        VaultOutcome.Created, VaultOutcome.Recreated, VaultOutcome.Unchanged -> ""
        is VaultOutcome.Refused ->
            when (outcome.reason) {
                RefuseReason.LENGTH -> PASSPHRASE_LENGTH
                RefuseReason.COMMON -> PASSPHRASE_COMMON
                RefuseReason.MEMORY -> MEMORY_FLOOR
                RefuseReason.HARDWARE -> HARDWARE_WARNING
                RefuseReason.DAMAGED -> ""
            }
        is VaultOutcome.Wrong, is VaultOutcome.Waiting -> PASSPHRASE_WAIT
    }
