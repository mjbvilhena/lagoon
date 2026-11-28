package com.mjbvilhena.lagoon

import com.mjbvilhena.lagoon.data.crypto.AesGcmKeyCheck
import com.mjbvilhena.lagoon.data.crypto.AssetCommonPasswords
import com.mjbvilhena.lagoon.data.crypto.FileVaultKeyStore
import com.mjbvilhena.lagoon.data.item.ItemStore
import com.mjbvilhena.lagoon.data.item.openInMemoryVault
import com.mjbvilhena.lagoon.domain.vault.Clock
import com.mjbvilhena.lagoon.domain.vault.DeriveOutcome
import com.mjbvilhena.lagoon.domain.vault.KeyWrapper
import com.mjbvilhena.lagoon.domain.vault.PersistedVaultKey
import com.mjbvilhena.lagoon.domain.vault.RetryState
import com.mjbvilhena.lagoon.domain.vault.RootKeyDeriver
import com.mjbvilhena.lagoon.domain.vault.SALT_BYTES
import com.mjbvilhena.lagoon.domain.vault.SaltSource
import com.mjbvilhena.lagoon.domain.vault.VaultHeader
import com.mjbvilhena.lagoon.domain.vault.VaultKeySetup
import com.mjbvilhena.lagoon.domain.vault.VaultKeyStore
import com.mjbvilhena.lagoon.domain.vault.WrapInfo
import com.mjbvilhena.lagoon.ui.VaultSession
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class ReadyVaultApplication : LagoonApplication() {
    override fun newVault(): VaultSession = session(this, hardware = true, seeded = true, allocate = true)
}

class PassphraseVaultApplication : LagoonApplication() {
    override fun newVault(): VaultSession = session(this, hardware = false, seeded = false, allocate = true)
}

class LowMemoryVaultApplication : LagoonApplication() {
    override fun newVault(): VaultSession = session(this, hardware = false, seeded = false, allocate = false)
}

private fun session(
    app: LagoonApplication,
    hardware: Boolean,
    seeded: Boolean,
    allocate: Boolean,
): VaultSession {
    val wrapper = ScreenWrap(hardware)
    val store: VaultKeyStore = if (seeded) MemoryKeyStore() else FileVaultKeyStore(app)
    val passwords =
        if (seeded) {
            com.mjbvilhena.lagoon.domain.vault
                .CommonPasswordList { false }
        } else {
            AssetCommonPasswords(app)
        }
    val keys =
        VaultKeySetup(
            deriver = ScreenDeriver(allocate),
            wrapper = wrapper,
            check = AesGcmKeyCheck(),
            store = store,
            passwords = passwords,
            clock = Clock { System.currentTimeMillis() },
            salts = SaltSource { ByteArray(SALT_BYTES) { 4 } },
        )
    if (seeded) {
        val header = VaultHeader.floor(ByteArray(SALT_BYTES) { 1 })
        val root = ByteArray(32) { 2 }
        wrapper.ensureWrappingKey()
        val memory = store as MemoryKeyStore
        memory.write(PersistedVaultKey(header, wrapper.wrap(root), AesGcmKeyCheck().seal(root, header.encode())))
    }
    return RoomVaultSession(ItemStore(openInMemoryVault(app)), keys)
}

private class ScreenDeriver(
    private val allocate: Boolean,
) : RootKeyDeriver {
    override fun derive(
        passphrase: ByteArray,
        header: VaultHeader,
    ): DeriveOutcome {
        if (!allocate) {
            return DeriveOutcome.AllocationFailed
        }
        if (!header.withinBounds()) {
            return DeriveOutcome.OutOfBounds
        }
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(header.salt, "HmacSHA256"))
        return DeriveOutcome.Key(mac.doFinal(passphrase))
    }
}

private class ScreenWrap(
    private val hardware: Boolean,
) : KeyWrapper {
    var lastRoot: ByteArray? = null
    private var alive = false

    override fun ensureWrappingKey(): WrapInfo {
        alive = true
        return info()
    }

    override fun current(): WrapInfo? = if (alive) info() else null

    override fun wrap(root: ByteArray): ByteArray {
        lastRoot = root.copyOf()
        return root.map { (it.toInt() xor 0x5a).toByte() }.toByteArray()
    }

    override fun unwrap(blob: ByteArray): ByteArray? {
        val root = lastRoot ?: return null
        val expected = root.map { (it.toInt() xor 0x5a).toByte() }.toByteArray()
        return if (blob.contentEquals(expected)) root.copyOf() else null
    }

    override fun deleteWrappingKey() {
        alive = false
        lastRoot = null
    }

    override fun present(): Boolean = alive

    private fun info(): WrapInfo = WrapInfo(hardware, false, false, false)
}

private class MemoryKeyStore : VaultKeyStore {
    private var record: PersistedVaultKey? = null
    private var retry = RetryState.CLEAR

    override fun read(): PersistedVaultKey? = record

    override fun write(record: PersistedVaultKey) {
        this.record = record
    }

    override fun readRetry(): RetryState = retry

    override fun writeRetry(state: RetryState) {
        retry = state
    }

    override fun storedBytes(): List<ByteArray> = emptyList()
}
