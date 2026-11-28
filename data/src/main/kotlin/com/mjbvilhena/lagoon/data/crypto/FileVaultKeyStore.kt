package com.mjbvilhena.lagoon.data.crypto

import android.content.Context
import com.mjbvilhena.lagoon.domain.vault.PersistedVaultKey
import com.mjbvilhena.lagoon.domain.vault.RetryState
import com.mjbvilhena.lagoon.domain.vault.VaultHeader
import com.mjbvilhena.lagoon.domain.vault.VaultKeyStore
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File

class FileVaultKeyStore(
    private val directory: File,
) : VaultKeyStore {
    constructor(context: Context) : this(File(context.filesDir, "vault-key"))

    override fun read(): PersistedVaultKey? {
        val headerFile = file(HEADER)
        if (!headerFile.isFile) {
            return null
        }
        val header = VaultHeader.decode(headerFile.readBytes()) ?: damagedHeader()
        val wrap = file(WRAP).takeIf { it.isFile }?.readBytes() ?: ByteArray(0)
        val verifier = file(VERIFIER).takeIf { it.isFile }?.readBytes() ?: ByteArray(0)
        return PersistedVaultKey(header, wrap, verifier)
    }

    override fun write(record: PersistedVaultKey) {
        directory.mkdirs()
        file(HEADER).writeBytes(record.header.encode())
        file(WRAP).writeBytes(record.wrap)
        file(VERIFIER).writeBytes(record.verifier)
    }

    override fun readRetry(): RetryState {
        val retry = file(RETRY)
        if (!retry.isFile) {
            return RetryState.CLEAR
        }
        return try {
            DataInputStream(retry.inputStream()).use { input ->
                RetryState(input.readInt(), input.readLong())
            }
        } catch (_: Exception) {
            RetryState.CLEAR
        }
    }

    override fun writeRetry(state: RetryState) {
        directory.mkdirs()
        DataOutputStream(file(RETRY).outputStream()).use { output ->
            output.writeInt(state.wrongCount)
            output.writeLong(state.nextAllowedAtMillis)
        }
    }

    override fun storedBytes(): List<ByteArray> =
        listOf(HEADER, WRAP, VERIFIER, RETRY).mapNotNull { name ->
            file(name).takeIf { it.isFile }?.readBytes()
        }

    private fun file(name: String): File = File(directory, name)

    private fun damagedHeader(): VaultHeader =
        VaultHeader(
            kdfId = "",
            argon2Version = 0,
            salt = ByteArray(0),
            memoryKiB = 0,
            iterations = 0,
            parallelism = 0,
            outputLength = 0,
            keyVersion = 0,
        )

    private companion object {
        const val HEADER = "header.bin"
        const val WRAP = "wrap.bin"
        const val VERIFIER = "verifier.bin"
        const val RETRY = "retry.bin"
    }
}
