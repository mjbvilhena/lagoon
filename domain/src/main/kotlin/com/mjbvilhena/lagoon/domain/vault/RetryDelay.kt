package com.mjbvilhena.lagoon.domain.vault

class RetryState(
    val wrongCount: Int,
    val nextAllowedAtMillis: Long,
) {
    fun afterWrong(nowMillis: Long): RetryState {
        val seconds = waitSeconds(wrongCount)
        return RetryState(wrongCount + 1, nowMillis + seconds * 1_000L)
    }

    fun secondsRemaining(nowMillis: Long): Long {
        if (nextAllowedAtMillis <= nowMillis) {
            return 0
        }
        val millis = nextAllowedAtMillis - nowMillis
        return (millis + 999L) / 1_000L
    }

    companion object {
        val CLEAR = RetryState(0, 0L)

        fun waitSeconds(wrongCountBefore: Int): Int =
            when (wrongCountBefore) {
                0 -> 2
                1 -> 4
                2 -> 8
                3 -> 16
                else -> 32
            }
    }
}

/** The phone delay does not apply to an offline guess against a copied file. */
fun delayAppliesOffPhone(): Boolean = false
