package com.mjbvilhena.lagoon.data.crypto

import android.os.Build
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [26])
class WrappingKeySpec26Test {
    @Test
    fun api26DoesNotSetUnlockedDeviceRequired() {
        val spec = wrappingKeySpec()
        assertFalse(spec.isUserAuthenticationRequired)
        assertEquals(26, Build.VERSION.SDK_INT)
    }
}
