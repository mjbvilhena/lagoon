package com.mjbvilhena.lagoon.ui

import android.app.Activity
import android.os.Bundle

/** Exported share target required by US-80. It does not read or store a share. */
class ShareTargetActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finish()
    }
}
