package com.mjbvilhena.lagoon.ui

import android.app.Activity
import android.os.Bundle

/** Exported launcher activity required by US-80. It does not request permissions. */
class LauncherActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ReleaseLogProbe.touch()
    }
}
