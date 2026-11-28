package com.mjbvilhena.lagoon.data.crypto

import android.os.Build
import android.security.keystore.KeyProperties
import androidx.test.core.app.ApplicationProvider
import com.lambdapioneer.argon2kt.Argon2Mode
import com.lambdapioneer.argon2kt.Argon2Version
import com.mjbvilhena.lagoon.domain.vault.ARGON2_VERSION
import com.mjbvilhena.lagoon.domain.vault.DeriveOutcome
import com.mjbvilhena.lagoon.domain.vault.FLOOR_ITERATIONS
import com.mjbvilhena.lagoon.domain.vault.FLOOR_MEMORY_KIB
import com.mjbvilhena.lagoon.domain.vault.ROOT_KEY_BYTES
import com.mjbvilhena.lagoon.domain.vault.RetryState
import com.mjbvilhena.lagoon.domain.vault.VaultHeader
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File
import java.nio.charset.StandardCharsets

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class VaultCryptoTest {
    @Test
    fun theDeriverPassesTheAdrParametersAndSkipsAnOutOfBoundsHeader() {
        val hash = RecordingHash()
        val deriver = Argon2RootDeriver(hash)
        val floor = VaultHeader.floor(ByteArray(16) { 3 })
        val low =
            VaultHeader(
                floor.kdfId,
                floor.argon2Version,
                floor.salt,
                memoryKiB = 65535,
                iterations = floor.iterations,
                parallelism = floor.parallelism,
                outputLength = floor.outputLength,
                keyVersion = floor.keyVersion,
            )
        assertTrue(deriver.derive(ByteArray(4), low) is DeriveOutcome.OutOfBounds)
        assertTrue(hash.calls.isEmpty())
        val header = VaultHeader.floor(ByteArray(16) { 4 })
        val outcome = deriver.derive("passphrase-ok".toByteArray(StandardCharsets.UTF_8), header)
        assertTrue(outcome is DeriveOutcome.Key)
        val call = hash.calls.single()
        assertEquals(Argon2Mode.ARGON2_ID.identifier, call.mode)
        assertEquals(Argon2Version.V13.version, call.version)
        assertEquals(ARGON2_VERSION, call.version)
        assertEquals(FLOOR_MEMORY_KIB, call.memoryKiB)
        assertEquals(FLOOR_ITERATIONS, call.iterations)
        assertEquals(1, call.parallelism)
        assertEquals(ROOT_KEY_BYTES, call.outputLength)
    }

    @Test
    fun theWrappingKeySpecDoesNotRequireAuthOrBiometricInvalidation() {
        val spec = wrappingKeySpec()
        assertFalse(spec.isUserAuthenticationRequired)
        assertFalse(spec.isUnlockedDeviceRequired)
        assertEquals(256, spec.keySize)
        assertEquals(KeyProperties.BLOCK_MODE_GCM, spec.blockModes.single())
        val source =
            listOf(
                File("src/main/kotlin/com/mjbvilhena/lagoon/data/crypto/AndroidVaultWrap.kt"),
                File("data/src/main/kotlin/com/mjbvilhena/lagoon/data/crypto/AndroidVaultWrap.kt"),
            ).first { it.isFile }
        val text = source.readText()
        assertFalse(text.contains("setInvalidatedByBiometricEnrollment"))
        assertTrue(text.contains("Cipher.ENCRYPT_MODE, secretKey()"))
    }

    @Test
    fun thePasswordListIsTheFilteredNcscAsset() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val passwords = AssetCommonPasswords(context)
        assertEquals(1212, passwords.size())
        assertTrue(passwords.blocks("q1w2e3r4t5y6"))
        assertTrue(passwords.blocks("pe#5gz29ptzmse"))
        assertFalse(passwords.blocks("q1w2e3r4t5y6 "))
        val notice =
            context.assets
                .open("common-passwords/OGL-v3-notice.txt")
                .bufferedReader()
                .use { it.readText() }
        assertTrue(notice.contains("Open Government Licence v3.0"))
        assertTrue(notice.contains("NCSC"))
        val bytes = context.assets.open("common-passwords/ncsc-12-128.txt").readBytes()
        assertEquals(18156, bytes.size)
        assertEquals(1212, bytes.count { it == '\n'.code.toByte() })
    }

    @Test
    fun retryStateSurvivesANewStore() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val directory = File(context.filesDir, "retry")
        val first = FileVaultKeyStore(directory)
        first.writeRetry(RetryState(2, 4_000L))
        val second = FileVaultKeyStore(directory)
        assertEquals(2, second.readRetry().wrongCount)
        assertEquals(4_000L, second.readRetry().nextAllowedAtMillis)
    }

    @Test
    fun hardwareLevelFollowsTheApiRule() {
        assertTrue(
            hardwareBacked(
                31,
                KeyProperties.SECURITY_LEVEL_TRUSTED_ENVIRONMENT,
                insideSecureHardware = false,
            ),
        )
        assertTrue(
            hardwareBacked(
                31,
                KeyProperties.SECURITY_LEVEL_STRONGBOX,
                insideSecureHardware = false,
            ),
        )
        assertFalse(
            hardwareBacked(
                31,
                KeyProperties.SECURITY_LEVEL_SOFTWARE,
                insideSecureHardware = true,
            ),
        )
        assertTrue(hardwareBacked(26, KeyProperties.SECURITY_LEVEL_SOFTWARE, insideSecureHardware = true))
        assertFalse(hardwareBacked(26, KeyProperties.SECURITY_LEVEL_SOFTWARE, insideSecureHardware = false))
    }

    @Test
    fun thisProcessSdkIsAtLeastTheUnlockedDeviceApi() {
        assertTrue(Build.VERSION.SDK_INT >= 28)
    }
}

private class RecordingHash : Argon2Hash {
    val calls = ArrayList<Call>()

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
        calls.add(Call(mode, version, iterations, memoryKiB, parallelism, outputLength))
        return ByteArray(outputLength) { 1 }
    }

    class Call(
        val mode: Int,
        val version: Int,
        val iterations: Int,
        val memoryKiB: Int,
        val parallelism: Int,
        val outputLength: Int,
    )
}
