package com.mjbvilhena.lagoon.domain.vault

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.nio.charset.StandardCharsets
import java.text.Normalizer
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class VaultKeySetupTest {
    private val passwords =
        object : CommonPasswordList {
            private val exact = setOf("q1w2e3r4t5y6", "PE#5GZ29PTZMSE")
            private val folded = exact.map { UnicodeCaseFold.fold(it) }.toSet()

            override fun blocks(nfc: String): Boolean = blocksListedPassword(nfc, exact, folded)
        }

    @Test
    fun aShortOrListedPassphraseDoesNotReachTheKdf() {
        val harness = harness()
        assertEquals(RefuseReason.LENGTH, refused(harness.setup.acceptNew("short-pass", true)))
        assertEquals(RefuseReason.COMMON, refused(harness.setup.acceptNew("q1w2e3r4t5y6", true)))
        assertEquals(RefuseReason.COMMON, refused(harness.setup.acceptNew("pe#5gz29ptzmse", true)))
        assertTrue(harness.deriver.calls.isEmpty())
        assertNull(harness.store.read())
    }

    @Test
    fun nfcLengthIsCountedWithoutTrimming() {
        val combining = "aaaaaaaaaaa" + "e\u0301"
        assertEquals(13, combining.codePointCount(0, combining.length))
        val nfc = Normalizer.normalize(combining, Normalizer.Form.NFC)
        assertEquals(12, nfc.codePointCount(0, nfc.length))
        val harness = harness()
        assertTrue(harness.setup.acceptNew(combining, true) is VaultOutcome.Created)
        val spaced = "q1w2e3r4t5y6 "
        assertTrue(harness.setup.acceptNew(spaced, true) is VaultOutcome.Unchanged)
        assertFalse(passwords.blocks(Normalizer.normalize(spaced, Normalizer.Form.NFC)))
    }

    @Test
    fun caseFoldIsUnicodeDefaultNotALocaleFold() {
        assertEquals("ss", UnicodeCaseFold.fold("ß"))
        assertEquals("strasse", UnicodeCaseFold.fold("Straße"))
    }

    @Test
    fun aHeaderOutsideTheBoundsDoesNotRunTheKdfOrGetClamped() {
        val harness = harness()
        create(harness, PHRASE)
        val salt =
            harness.store
                .read()!!
                .header.salt
        listOf(
            header(salt, memoryKiB = 65535),
            header(salt, memoryKiB = 262145),
            header(salt, iterations = 2),
            header(salt, iterations = 11),
            header(salt, parallelism = 2),
            header(salt, argon2Version = 0x10),
            header(salt.copyOf(15)),
        ).forEach { bad ->
            harness.store.record = PersistedVaultKey(bad, ByteArray(0), ByteArray(0))
            val before = harness.deriver.calls.size
            assertEquals(RefuseReason.DAMAGED, refused(harness.setup.unlock(PHRASE)))
            assertEquals(before, harness.deriver.calls.size)
            assertEquals(
                bad.memoryKiB,
                harness.store
                    .read()!!
                    .header.memoryKiB,
            )
            assertEquals(
                bad.iterations,
                harness.store
                    .read()!!
                    .header.iterations,
            )
        }
        val raised = header(salt, memoryKiB = 70000, iterations = 4)
        assertTrue(raised.withinBounds())
        harness.store.record = PersistedVaultKey(raised, ByteArray(0), seal(harness, raised))
        harness.deriver.calls.clear()
        assertTrue(harness.setup.unlock(PHRASE) is VaultOutcome.Recreated)
        assertEquals(
            70000,
            harness.deriver.calls
                .single()
                .second.memoryKiB,
        )
        assertEquals(
            4,
            harness.deriver.calls
                .single()
                .second.iterations,
        )
    }

    @Test
    fun aNewVaultUsesTheFloorAndRefusesAFailedAllocation() {
        val harness = harness()
        create(harness, PHRASE)
        val written = harness.store.read()!!.header
        assertEquals(FLOOR_MEMORY_KIB, written.memoryKiB)
        assertEquals(FLOOR_ITERATIONS, written.iterations)
        assertEquals(ARGON2_PARALLELISM, written.parallelism)
        assertEquals(ROOT_KEY_BYTES, written.outputLength)
        assertEquals(ARGON2_VERSION, written.argon2Version)
        assertEquals(SALT_BYTES, written.salt.size)

        val empty = harness()
        empty.deriver.failAllocation = true
        assertEquals(RefuseReason.MEMORY, refused(empty.setup.acceptNew(PHRASE, true)))
        assertNull(empty.store.read())
        assertTrue(empty.deriver.calls.all { it.second.memoryKiB == FLOOR_MEMORY_KIB })
        assertTrue(empty.deriver.calls.none { it.second.memoryKiB < FLOOR_MEMORY_KIB })
        assertTrue(empty.wrapper.deleted)

        val again = harness()
        create(again, PHRASE)
        again.deriver.failAllocation = true
        again.deriver.calls.clear()
        assertEquals(RefuseReason.MEMORY, refused(again.setup.unlock(PHRASE)))
        assertEquals(
            FLOOR_MEMORY_KIB,
            again.store
                .read()!!
                .header.memoryKiB,
        )
        assertTrue(again.deriver.calls.all { it.second.memoryKiB == FLOOR_MEMORY_KIB })
    }

    @Test
    fun aWrongPassphraseWaitsAndACorrectOneResets() {
        val harness = harness()
        create(harness, PHRASE)
        val header =
            harness.store
                .read()!!
                .header
                .encode()
        val wrap =
            harness.store
                .read()!!
                .wrap
                .copyOf()
        harness.deriver.calls.clear()
        assertTrue(harness.setup.unlock("wrong-passphrase") is VaultOutcome.Wrong)
        assertTrue(
            harness.store
                .read()!!
                .header
                .encode()
                .contentEquals(header),
        )
        assertTrue(
            harness.store
                .read()!!
                .wrap
                .contentEquals(wrap),
        )
        val during = harness.deriver.calls.size
        assertTrue(harness.setup.unlock(PHRASE) is VaultOutcome.Waiting)
        assertEquals(during, harness.deriver.calls.size)
        val waits = ArrayList<Int>()
        repeat(6) {
            harness.clock.now = harness.store.retry.nextAllowedAtMillis
            val outcome = harness.setup.unlock("wrong-passphrase") as VaultOutcome.Wrong
            waits.add(outcome.waitSeconds)
        }
        assertEquals(listOf(4, 8, 16, 32, 32, 32), waits)
        harness.clock.now = harness.store.retry.nextAllowedAtMillis
        assertTrue(harness.setup.unlock(PHRASE) is VaultOutcome.Recreated)
        assertEquals(0, harness.store.readRetry().wrongCount)
        assertFalse(delayAppliesOffPhone())
    }

    @Test
    fun theDelaySurvivesANewSetupOnTheSameStore() {
        val harness = harness()
        create(harness, PHRASE)
        harness.setup.unlock("wrong-passphrase")
        val again =
            VaultKeySetup(
                harness.deriver,
                harness.wrapper,
                harness.check,
                harness.store,
                passwords,
                harness.clock,
                harness.salts,
            )
        assertTrue(again.unlock(PHRASE) is VaultOutcome.Waiting)
    }

    @Test
    fun declineDeletesTheSoftwareKeyAndWritesNothing() {
        val harness = harness()
        harness.setup.probeWrappingKey()
        assertTrue(harness.wrapper.present())
        harness.setup.decline()
        assertFalse(harness.wrapper.present())
        assertNull(harness.store.read())
        assertTrue(harness.setup.acceptNew(PHRASE, hardwareAccepted = false) is VaultOutcome.Refused)
        assertNull(harness.store.read())
        assertTrue(harness.wrapper.deleted)
    }

    @Test
    fun softwareNeedsTheWarningAndHardwareDoesNot() {
        val software = harness()
        software.setup.probeWrappingKey()
        assertTrue(software.setup.setupHardwareWarning())
        assertTrue(software.setup.acceptNew(PHRASE, hardwareAccepted = true) is VaultOutcome.Created)
        assertTrue(software.setup.showHardwareWarning())
        val hardware = harness(hardware = true)
        hardware.setup.probeWrappingKey()
        assertFalse(hardware.setup.setupHardwareWarning())
        assertTrue(hardware.setup.acceptNew(PHRASE, hardwareAccepted = false) is VaultOutcome.Created)
        assertFalse(hardware.setup.showHardwareWarning())
    }

    @Test
    fun aMissingWrapDerivesTheSameKeyFromTheHeader() {
        val harness = harness()
        create(harness, PHRASE)
        val salt =
            harness.store
                .read()!!
                .header.salt
                .copyOf()
        val first = harness.wrapper.lastRoot!!.copyOf()
        harness.wrapper.deleteWrappingKey()
        harness.deriver.calls.clear()
        assertTrue(harness.setup.unlock(PHRASE) is VaultOutcome.Recreated)
        assertTrue(
            harness.deriver.calls
                .single()
                .second.salt
                .contentEquals(salt),
        )
        assertTrue(harness.wrapper.lastRoot!!.contentEquals(first))
        assertFalse(
            harness.store.storedBytes().any { bytes ->
                bytes.toString(StandardCharsets.UTF_8).contains(PHRASE)
            },
        )
    }

    @Test
    fun aNewEmptyVaultWritesNothing() {
        val harness = harness()
        create(harness, PHRASE)
        val before =
            harness.store
                .read()!!
                .header
                .encode()
        assertTrue(harness.setup.newEmptyVault() is VaultOutcome.Unchanged)
        assertTrue(
            harness.store
                .read()!!
                .header
                .encode()
                .contentEquals(before),
        )
    }

    @Test
    fun aTruncatedHeaderIsMissingAField() {
        val floor = VaultHeader.floor(ByteArray(SALT_BYTES) { 1 }).encode()
        assertNull(VaultHeader.decode(floor.copyOf(floor.size - 4)))
    }

    private fun refused(outcome: VaultOutcome): RefuseReason = (outcome as VaultOutcome.Refused).reason

    private fun create(
        harness: Harness,
        passphrase: String,
    ) {
        assertTrue(harness.setup.acceptNew(passphrase, true) is VaultOutcome.Created)
    }

    private fun seal(
        harness: Harness,
        header: VaultHeader,
    ): ByteArray {
        val derived = harness.deriver.derive(PHRASE.toByteArray(StandardCharsets.UTF_8), header) as DeriveOutcome.Key
        return harness.check.seal(derived.bytes, header.encode())
    }

    private fun header(
        salt: ByteArray,
        memoryKiB: Int = FLOOR_MEMORY_KIB,
        iterations: Int = FLOOR_ITERATIONS,
        parallelism: Int = ARGON2_PARALLELISM,
        argon2Version: Int = ARGON2_VERSION,
    ): VaultHeader =
        VaultHeader(
            kdfId = KDF_ARGON2ID,
            argon2Version = argon2Version,
            salt = salt,
            memoryKiB = memoryKiB,
            iterations = iterations,
            parallelism = parallelism,
            outputLength = ROOT_KEY_BYTES,
            keyVersion = FIRST_KEY_VERSION,
        )

    private fun harness(hardware: Boolean = false): Harness {
        val deriver = RecordingDeriver()
        val wrapper = MemoryWrap(hardware)
        val check = HmacCheck()
        val store = MemoryKeyStore()
        val clock = MutableClock()
        val salts = SaltSource { ByteArray(SALT_BYTES) { 9 } }
        val setup = VaultKeySetup(deriver, wrapper, check, store, passwords, clock, salts)
        return Harness(setup, deriver, wrapper, check, store, clock, salts)
    }

    private class Harness(
        val setup: VaultKeySetup,
        val deriver: RecordingDeriver,
        val wrapper: MemoryWrap,
        val check: KeyCheck,
        val store: MemoryKeyStore,
        val clock: MutableClock,
        val salts: SaltSource,
    )
}

private const val PHRASE = "vault-fixture-phrase"

private class RecordingDeriver : RootKeyDeriver {
    val calls = ArrayList<Pair<ByteArray, VaultHeader>>()
    var failAllocation = false

    override fun derive(
        passphrase: ByteArray,
        header: VaultHeader,
    ): DeriveOutcome {
        calls.add(passphrase.copyOf() to header)
        if (!header.withinBounds()) {
            return DeriveOutcome.OutOfBounds
        }
        if (failAllocation) {
            return DeriveOutcome.AllocationFailed
        }
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(header.salt, "HmacSHA256"))
        return DeriveOutcome.Key(mac.doFinal(passphrase))
    }
}

private class MemoryWrap(
    private val hardware: Boolean,
) : KeyWrapper {
    var deleted = false
    var lastRoot: ByteArray? = null
    private var alive = false

    override fun ensureWrappingKey(): WrapInfo {
        alive = true
        deleted = false
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
        deleted = true
        lastRoot = null
    }

    override fun present(): Boolean = alive

    private fun info(): WrapInfo = WrapInfo(hardware, false, false, false)
}

private class MemoryKeyStore : VaultKeyStore {
    var record: PersistedVaultKey? = null
    var retry: RetryState = RetryState.CLEAR

    override fun read(): PersistedVaultKey? = record

    override fun write(record: PersistedVaultKey) {
        this.record = record
    }

    override fun readRetry(): RetryState = retry

    override fun writeRetry(state: RetryState) {
        retry = state
    }

    override fun storedBytes(): List<ByteArray> {
        val current = record
        val retryBytes = ByteArray(0)
        if (current == null) {
            return listOf(retryBytes)
        }
        return listOf(current.header.encode(), current.wrap, current.verifier, retryBytes)
    }
}

private class MutableClock(
    var now: Long = 1_000_000L,
) : Clock {
    override fun nowMillis(): Long = now
}

private class HmacCheck : KeyCheck {
    override fun seal(
        root: ByteArray,
        aad: ByteArray,
    ): ByteArray {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(root, "HmacSHA256"))
        return mac.doFinal(aad)
    }

    override fun opens(
        root: ByteArray,
        sealed: ByteArray,
        aad: ByteArray,
    ): Boolean = sealed.contentEquals(seal(root, aad))
}
