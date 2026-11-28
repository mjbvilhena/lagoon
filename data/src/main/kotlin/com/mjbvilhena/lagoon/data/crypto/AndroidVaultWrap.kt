package com.mjbvilhena.lagoon.data.crypto

import android.annotation.SuppressLint
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyInfo
import android.security.keystore.KeyProperties
import com.mjbvilhena.lagoon.domain.vault.KeyWrapper
import com.mjbvilhena.lagoon.domain.vault.WrapInfo
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec

const val VAULT_WRAP_ALIAS = "lagoon.vault.wrap"

private const val ANDROID_KEY_STORE = "AndroidKeyStore"

private const val GCM = "AES/GCM/NoPadding"

private const val GCM_TAG_BITS = 128

private const val GCM_NONCE_BYTES = 12

fun hardwareBacked(
    sdk: Int,
    securityLevel: Int,
    insideSecureHardware: Boolean,
): Boolean =
    if (sdk >= 31) {
        securityLevel == KeyProperties.SECURITY_LEVEL_STRONGBOX ||
            securityLevel == KeyProperties.SECURITY_LEVEL_TRUSTED_ENVIRONMENT
    } else {
        insideSecureHardware
    }

fun wrappingKeySpec(alias: String = VAULT_WRAP_ALIAS): KeyGenParameterSpec {
    val builder =
        KeyGenParameterSpec
            .Builder(
                alias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT,
            ).setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .setUserAuthenticationRequired(false)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        builder.setUnlockedDeviceRequired(false)
    }
    return builder.build()
}

class AndroidVaultWrap : KeyWrapper {
    override fun ensureWrappingKey(): WrapInfo {
        val store = keyStore()
        if (!store.containsAlias(VAULT_WRAP_ALIAS)) {
            val generator =
                javax.crypto.KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    ANDROID_KEY_STORE,
                )
            generator.init(wrappingKeySpec())
            generator.generateKey()
        }
        return readInfo()
    }

    override fun current(): WrapInfo? = if (present()) readInfo() else null

    override fun wrap(root: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(GCM)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey())
        val nonce = cipher.iv
        val ciphertext = cipher.doFinal(root)
        return nonce + ciphertext
    }

    override fun unwrap(blob: ByteArray): ByteArray? {
        if (blob.size <= GCM_NONCE_BYTES || !present()) {
            return null
        }
        return try {
            val cipher = Cipher.getInstance(GCM)
            cipher.init(
                Cipher.DECRYPT_MODE,
                secretKey(),
                GCMParameterSpec(GCM_TAG_BITS, blob.copyOfRange(0, GCM_NONCE_BYTES)),
            )
            cipher.doFinal(blob.copyOfRange(GCM_NONCE_BYTES, blob.size))
        } catch (_: Exception) {
            null
        }
    }

    override fun deleteWrappingKey() {
        val store = keyStore()
        if (store.containsAlias(VAULT_WRAP_ALIAS)) {
            store.deleteEntry(VAULT_WRAP_ALIAS)
        }
    }

    override fun present(): Boolean = keyStore().containsAlias(VAULT_WRAP_ALIAS)

    @SuppressLint("NewApi")
    private fun unlockedDeviceRequired(info: KeyInfo): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            return false
        }
        return info.isUnlockedDeviceRequired
    }

    @Suppress("DEPRECATION")
    private fun readInfo(): WrapInfo {
        val key = secretKey()
        val factory = SecretKeyFactory.getInstance(key.algorithm, ANDROID_KEY_STORE)
        val info = factory.getKeySpec(key, KeyInfo::class.java) as KeyInfo
        val level =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                info.securityLevel
            } else {
                KeyProperties.SECURITY_LEVEL_UNKNOWN
            }
        val unlocked = unlockedDeviceRequired(info)
        return WrapInfo(
            hardwareBacked = hardwareBacked(Build.VERSION.SDK_INT, level, info.isInsideSecureHardware),
            userAuthenticationRequired = info.isUserAuthenticationRequired,
            unlockedDeviceRequired = unlocked,
            invalidatedByBiometricEnrollment = info.isInvalidatedByBiometricEnrollment,
        )
    }

    private fun secretKey(): SecretKey = keyStore().getKey(VAULT_WRAP_ALIAS, null) as SecretKey

    private fun keyStore(): KeyStore =
        KeyStore.getInstance(ANDROID_KEY_STORE).apply {
            load(null)
        }
}
