package com.mjbvilhena.lagoon.data.crypto

import com.mjbvilhena.lagoon.domain.vault.KeyCheck
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

private val CHECK_TEXT = "lagoon-key-check".toByteArray(Charsets.US_ASCII)

class AesGcmKeyCheck(
    private val random: SecureRandom = SecureRandom(),
) : KeyCheck {
    override fun seal(
        root: ByteArray,
        aad: ByteArray,
    ): ByteArray {
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(root, "AES"), random)
        cipher.updateAAD(aad)
        val nonce = cipher.iv
        return nonce + cipher.doFinal(CHECK_TEXT)
    }

    override fun opens(
        root: ByteArray,
        sealed: ByteArray,
        aad: ByteArray,
    ): Boolean {
        if (sealed.size <= 12) {
            return false
        }
        return try {
            val cipher = Cipher.getInstance("AES/GCM/NoPadding")
            cipher.init(
                Cipher.DECRYPT_MODE,
                SecretKeySpec(root, "AES"),
                GCMParameterSpec(128, sealed.copyOfRange(0, 12)),
            )
            cipher.updateAAD(aad)
            val opened = cipher.doFinal(sealed.copyOfRange(12, sealed.size))
            opened.contentEquals(CHECK_TEXT)
        } catch (_: Exception) {
            false
        }
    }
}
