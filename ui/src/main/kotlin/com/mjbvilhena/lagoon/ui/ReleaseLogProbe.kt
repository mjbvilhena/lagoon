package com.mjbvilhena.lagoon.ui

import android.util.Log

/**
 * A live Log call so the release shrinker has something to strip.
 * The release APK must not contain the sentinel. The debug APK must.
 */
internal object ReleaseLogProbe {
    fun touch() {
        Log.d("Lagoon", "LOG_PROBE_SENTINEL")
    }
}
