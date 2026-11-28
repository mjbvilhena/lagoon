package com.mjbvilhena.lagoon.domain.vault

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

const val KDF_ARGON2ID = "argon2id"

const val ARGON2_VERSION = 0x13

const val FLOOR_MEMORY_KIB = 65536

const val CEILING_MEMORY_KIB = 262144

const val FLOOR_ITERATIONS = 3

const val CEILING_ITERATIONS = 10

const val ARGON2_PARALLELISM = 1

const val SALT_BYTES = 16

const val ROOT_KEY_BYTES = 32

const val FIRST_KEY_VERSION = 1L

private val MAGIC = byteArrayOf(0x4c, 0x41, 0x47, 0x4f, 0x4f, 0x4e, 0x00)

private const val HEADER_VERSION = 1

class VaultHeader(
    val kdfId: String,
    val argon2Version: Int,
    val salt: ByteArray,
    val memoryKiB: Int,
    val iterations: Int,
    val parallelism: Int,
    val outputLength: Int,
    val keyVersion: Long,
) {
    fun withinBounds(): Boolean =
        kdfId == KDF_ARGON2ID &&
            argon2Version == ARGON2_VERSION &&
            salt.size == SALT_BYTES &&
            memoryKiB in FLOOR_MEMORY_KIB..CEILING_MEMORY_KIB &&
            iterations in FLOOR_ITERATIONS..CEILING_ITERATIONS &&
            parallelism == ARGON2_PARALLELISM &&
            outputLength == ROOT_KEY_BYTES &&
            keyVersion in 0L..0xffff_ffffL

    fun encode(): ByteArray {
        val kdf = kdfId.toByteArray(StandardCharsets.UTF_8)
        val buffer =
            ByteBuffer.allocate(7 + 2 + 1 + kdf.size + 2 + SALT_BYTES + 4 * 5)
        buffer.put(MAGIC)
        buffer.putShort(HEADER_VERSION.toShort())
        buffer.put(kdf.size.toByte())
        buffer.put(kdf)
        buffer.putShort(argon2Version.toShort())
        buffer.put(salt)
        buffer.putInt(memoryKiB)
        buffer.putInt(iterations)
        buffer.putInt(parallelism)
        buffer.putInt(outputLength)
        buffer.putInt(keyVersion.toInt())
        return buffer.array()
    }

    companion object {
        fun floor(salt: ByteArray): VaultHeader =
            VaultHeader(
                kdfId = KDF_ARGON2ID,
                argon2Version = ARGON2_VERSION,
                salt = salt.copyOf(),
                memoryKiB = FLOOR_MEMORY_KIB,
                iterations = FLOOR_ITERATIONS,
                parallelism = ARGON2_PARALLELISM,
                outputLength = ROOT_KEY_BYTES,
                keyVersion = FIRST_KEY_VERSION,
            )

        fun decode(bytes: ByteArray): VaultHeader? {
            val buffer = ByteBuffer.wrap(bytes)
            if (buffer.remaining() < MAGIC.size + 2 + 1) {
                return null
            }
            val magic = ByteArray(MAGIC.size)
            buffer.get(magic)
            if (!magic.contentEquals(MAGIC)) {
                return null
            }
            val version = buffer.short.toInt() and 0xffff
            if (version != HEADER_VERSION) {
                return null
            }
            val kdfLength = buffer.get().toInt() and 0xff
            if (buffer.remaining() < kdfLength + 2 + SALT_BYTES + 20) {
                return null
            }
            val kdfBytes = ByteArray(kdfLength)
            buffer.get(kdfBytes)
            val argon2Version = buffer.short.toInt() and 0xffff
            val salt = ByteArray(SALT_BYTES)
            buffer.get(salt)
            if (buffer.remaining() < 20) {
                return null
            }
            return VaultHeader(
                kdfId = kdfBytes.toString(StandardCharsets.UTF_8),
                argon2Version = argon2Version,
                salt = salt,
                memoryKiB = buffer.int,
                iterations = buffer.int,
                parallelism = buffer.int,
                outputLength = buffer.int,
                keyVersion = buffer.int.toLong() and 0xffff_ffffL,
            )
        }
    }
}
