package com.mjbvilhena.lagoon.spike

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

/** On-device US-57 runner. It does not use the network and stores no personal data. */
class SpikeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val result = text("Running the checks.", 28f)
        val detail = text("", 18f)
        val device = text(deviceLine(), 18f)
        val column =
            LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(48, 48, 48, 48)
                addView(text("Lagoon spike", 20f))
                addView(result)
                addView(detail)
                addView(device)
            }
        setContentView(ScrollView(this).apply { addView(column) })
        Thread {
            val report = FtsSqlCipherProbe.run(applicationContext)
            runOnUiThread {
                val word = if (report.passed) "PASS" else "FAIL"
                result.text = word
                result.contentDescription = word
                detail.text = report.detail
                detail.contentDescription = report.detail
            }
        }.start()
    }

    private fun deviceLine(): String {
        val abi = Build.SUPPORTED_ABIS.joinToString(", ")
        return "Model: ${Build.MANUFACTURER} ${Build.MODEL}\nABI: $abi\nAndroid: ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"
    }

    private fun text(
        value: String,
        size: Float,
    ): TextView =
        TextView(this).apply {
            text = value
            contentDescription = value
            textSize = size
            setPadding(0, 24, 0, 0)
        }
}
