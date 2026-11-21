package com.mjbvilhena.lagoon.spike

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FtsSqlCipherProbeTest {
    @Test
    fun fts5AndSqlCipherPassOnThisDevice() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val report = FtsSqlCipherProbe.run(context)
        assertTrue(report.detail, report.passed)
    }
}
