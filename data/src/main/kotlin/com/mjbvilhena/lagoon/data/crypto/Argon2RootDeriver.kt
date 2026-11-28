package com.mjbvilhena.lagoon.data.crypto

import com.lambdapioneer.argon2kt.Argon2Exception
import com.lambdapioneer.argon2kt.Argon2Kt
import com.lambdapioneer.argon2kt.Argon2Mode
import com.lambdapioneer.argon2kt.Argon2Version
import com.mjbvilhena.lagoon.domain.vault.DeriveOutcome
import com.mjbvilhena.lagoon.domain.vault.RootKeyDeriver
import com.mjbvilhena.lagoon.domain.vault.VaultHeader

/**
 * Every call passes Argon2id, version 0x13, and the header's memory, iterations,
 * parallelism, and output length. Library defaults are not used.
 */
fun interface Argon2Hash {
    fun hash(
        mode: Int,
        version: Int,
        password: ByteArray,
        salt: ByteArray,
        iterations: Int,
        memoryKiB: Int,
        parallelism: Int,
        outputLength: Int,
    ): ByteArray
}

class Argon2KtHash : Argon2Hash {
    private val argon2 by lazy { Argon2Kt() }

    override fun hash(
        mode: Int,
        version: Int,
        password: ByteArray,
        salt: ByteArray,
        iterations: Int,
        memoryKiB: Int,
        parallelism: Int,
        outputLength: Int,
    ): ByteArray {
        val result =
            argon2.hash(
                mode = argon2Mode(mode),
                password = password,
                salt = salt,
                tCostInIterations = iterations,
                mCostInKibibyte = memoryKiB,
                parallelism = parallelism,
                hashLengthInBytes = outputLength,
                version = argon2Version(version),
            )
        return result.rawHashAsByteArray()
    }
}

class Argon2RootDeriver(
    private val hash: Argon2Hash = Argon2KtHash(),
) : RootKeyDeriver {
    override fun derive(
        passphrase: ByteArray,
        header: VaultHeader,
    ): DeriveOutcome {
        if (!header.withinBounds()) {
            return DeriveOutcome.OutOfBounds
        }
        return try {
            val key =
                hash.hash(
                    mode = Argon2Mode.ARGON2_ID.identifier,
                    version = Argon2Version.V13.version,
                    password = passphrase,
                    salt = header.salt,
                    iterations = header.iterations,
                    memoryKiB = header.memoryKiB,
                    parallelism = header.parallelism,
                    outputLength = header.outputLength,
                )
            DeriveOutcome.Key(key)
        } catch (error: OutOfMemoryError) {
            DeriveOutcome.AllocationFailed
        } catch (error: Argon2Exception) {
            if (isAllocationFailure(error)) {
                DeriveOutcome.AllocationFailed
            } else {
                throw error
            }
        }
    }
}

private fun argon2Mode(mode: Int): Argon2Mode =
    when (mode) {
        Argon2Mode.ARGON2_ID.identifier -> Argon2Mode.ARGON2_ID
        Argon2Mode.ARGON2_I.identifier -> Argon2Mode.ARGON2_I
        Argon2Mode.ARGON2_D.identifier -> Argon2Mode.ARGON2_D
        else -> error("Argon2 mode is not a named mode")
    }

private fun argon2Version(version: Int): Argon2Version =
    when (version) {
        Argon2Version.V13.version -> Argon2Version.V13
        Argon2Version.V10.version -> Argon2Version.V10
        else -> error("Argon2 version is not 0x10 or 0x13")
    }

fun isAllocationFailure(error: Argon2Exception): Boolean {
    val text = error.message ?: return false
    return text.contains("ARGON2_MEMORY_ALLOCATION_ERROR") ||
        text.contains("ARGON2JNI_MALLOC_FAILED")
}
