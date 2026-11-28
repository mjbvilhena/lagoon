package com.mjbvilhena.lagoon.data.crypto

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lambdapioneer.argon2kt.Argon2Kt
import com.lambdapioneer.argon2kt.Argon2Mode
import com.lambdapioneer.argon2kt.Argon2Version
import com.mjbvilhena.lagoon.domain.vault.FIXTURE_PASSPHRASE
import com.mjbvilhena.lagoon.domain.vault.wipe
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Times the native Argon2id call at the ADR-0001 floor.
 * A Nokia 3 (2017) median is not yet measured. This runner is not that phone.
 * The floor is not lowered from a result here.
 */
@RunWith(AndroidJUnit4::class)
class Argon2FloorBenchmark {
    @Test
    fun timeTheFloorCallThreeTimes() {
        val argon2 = Argon2Kt()
        val password = FIXTURE_PASSPHRASE.toByteArray(Charsets.UTF_8)
        val salt = ByteArray(16) { 0x3C }
        val times = ArrayList<Long>(3)
        try {
            repeat(3) {
                val started = System.nanoTime()
                val result =
                    argon2.hash(
                        mode = Argon2Mode.ARGON2_ID,
                        password = password,
                        salt = salt,
                        tCostInIterations = 3,
                        mCostInKibibyte = 65536,
                        parallelism = 1,
                        hashLengthInBytes = 32,
                        version = Argon2Version.V13,
                    )
                val elapsedMs = (System.nanoTime() - started) / 1_000_000L
                times.add(elapsedMs)
                result.rawHashAsByteArray().wipe()
            }
        } finally {
            password.wipe()
        }
        val median = times.sorted()[times.size / 2]
        println("argon2 floor times ms=$times median=$median nokia3=not-measured")
    }
}
