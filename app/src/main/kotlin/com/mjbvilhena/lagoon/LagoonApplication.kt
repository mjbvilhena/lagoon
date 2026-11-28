package com.mjbvilhena.lagoon

import android.app.Application
import com.mjbvilhena.lagoon.data.crypto.AesGcmKeyCheck
import com.mjbvilhena.lagoon.data.crypto.AndroidVaultWrap
import com.mjbvilhena.lagoon.data.crypto.Argon2RootDeriver
import com.mjbvilhena.lagoon.data.crypto.AssetCommonPasswords
import com.mjbvilhena.lagoon.data.crypto.FileVaultKeyStore
import com.mjbvilhena.lagoon.data.item.ItemStore
import com.mjbvilhena.lagoon.data.item.openInMemoryVault
import com.mjbvilhena.lagoon.domain.vault.SALT_BYTES
import com.mjbvilhena.lagoon.domain.vault.VaultKeySetup
import com.mjbvilhena.lagoon.ui.VaultHost
import com.mjbvilhena.lagoon.ui.VaultSession
import java.security.SecureRandom

open class LagoonApplication :
    Application(),
    VaultHost {
    override val vault: VaultSession by lazy { newVault() }

    protected open fun newVault(): VaultSession {
        val keys =
            VaultKeySetup(
                deriver = Argon2RootDeriver(),
                wrapper = AndroidVaultWrap(),
                check = AesGcmKeyCheck(),
                store = FileVaultKeyStore(this),
                passwords = AssetCommonPasswords(this),
                clock = { System.currentTimeMillis() },
                salts = {
                    val salt = ByteArray(SALT_BYTES)
                    SecureRandom().nextBytes(salt)
                    salt
                },
            )
        return RoomVaultSession(ItemStore(openInMemoryVault(this)), keys)
    }
}
