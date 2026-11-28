package com.mjbvilhena.lagoon.data.crypto

import android.content.Context
import com.mjbvilhena.lagoon.domain.vault.CommonPasswordList
import com.mjbvilhena.lagoon.domain.vault.UnicodeCaseFold
import com.mjbvilhena.lagoon.domain.vault.blocksListedPassword
import java.text.Normalizer

class AssetCommonPasswords(
    context: Context,
) : CommonPasswordList {
    private val exact: Set<String>
    private val folded: Set<String>

    init {
        val text =
            context.assets
                .open("common-passwords/ncsc-12-128.txt")
                .bufferedReader()
                .use { it.readText() }
        exact =
            text
                .split('\n')
                .filter { it.isNotEmpty() }
                .map { Normalizer.normalize(it, Normalizer.Form.NFC) }
                .toSet()
        folded = exact.map { UnicodeCaseFold.fold(it) }.toSet()
    }

    override fun blocks(nfc: String): Boolean = blocksListedPassword(nfc, exact, folded)

    fun size(): Int = exact.size
}
