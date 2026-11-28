package com.mjbvilhena.lagoon

import com.mjbvilhena.lagoon.data.item.ItemStore
import com.mjbvilhena.lagoon.domain.item.DetailContent
import com.mjbvilhena.lagoon.domain.item.EditResult
import com.mjbvilhena.lagoon.domain.item.Item
import com.mjbvilhena.lagoon.domain.item.ItemId
import com.mjbvilhena.lagoon.domain.item.ItemIds
import com.mjbvilhena.lagoon.domain.item.PersonalNote
import com.mjbvilhena.lagoon.domain.item.detailContent
import com.mjbvilhena.lagoon.domain.item.editItem
import com.mjbvilhena.lagoon.domain.item.homeItems
import com.mjbvilhena.lagoon.domain.item.moveToTrash
import com.mjbvilhena.lagoon.domain.share.NOTHING_SAVED
import com.mjbvilhena.lagoon.domain.share.SAVED_TO_LAGOON
import com.mjbvilhena.lagoon.domain.share.ShareDecision
import com.mjbvilhena.lagoon.domain.share.ShareInput
import com.mjbvilhena.lagoon.domain.share.decideShare
import com.mjbvilhena.lagoon.domain.vault.FORGOTTEN_SENTENCE
import com.mjbvilhena.lagoon.domain.vault.NO_VAULT_CREATED
import com.mjbvilhena.lagoon.domain.vault.VaultKeySetup
import com.mjbvilhena.lagoon.domain.vault.VaultOutcome
import com.mjbvilhena.lagoon.domain.vault.VaultPhase
import com.mjbvilhena.lagoon.domain.vault.outcomeMessage
import com.mjbvilhena.lagoon.ui.PASSPHRASE_REQUIRED
import com.mjbvilhena.lagoon.ui.PassphraseStep
import com.mjbvilhena.lagoon.ui.VaultSession
import java.time.Instant
import java.time.ZoneId
import java.util.UUID

internal class RoomVaultSession(
    private val store: ItemStore,
    private val keys: VaultKeySetup,
) : VaultSession {
    private val pendingDeletes = linkedSetOf<String>()
    private var heldShare: ShareInput? = null
    private var prepared = false
    private var memoryBlocked = false

    override fun acceptShare(
        input: ShareInput,
        now: Long,
        today: String,
    ): String {
        when (val decision = decide(input, now, today)) {
            is ShareDecision.Refuse -> return NOTHING_SAVED
            is ShareDecision.Store -> {
                if (keys.phase() != VaultPhase.READY) {
                    heldShare = input
                    return PASSPHRASE_REQUIRED
                }
                store.save(decision.item)
                return SAVED_TO_LAGOON
            }
        }
    }

    override fun home(): List<Item> = homeItems(store.all()).filter { it.id.value !in pendingDeletes }

    override fun detail(id: String): DetailContent? {
        val item = store.item(ItemId(id)) ?: return null
        return detailContent(item, sensitiveUnlocked = false)
    }

    override fun edit(
        id: String,
        title: String,
        body: String,
        notes: List<PersonalNote>,
        tags: List<String>,
        now: Long,
    ): EditResult {
        val item = store.item(ItemId(id)) ?: return EditResult.TitleRequired
        val result = editItem(item, title, body, notes, tags, now)
        if (result is EditResult.Saved) {
            store.save(result.item)
        }
        return result
    }

    override fun item(id: String): Item? = store.item(ItemId(id))

    override fun beginDelete(id: String) {
        pendingDeletes.add(id)
    }

    override fun undoDelete(id: String) {
        pendingDeletes.remove(id)
    }

    override fun confirmDelete(now: Long) {
        pendingDeletes.toList().forEach { id ->
            val item = store.item(ItemId(id)) ?: return@forEach
            store.save(moveToTrash(item, now))
        }
        pendingDeletes.clear()
    }

    override fun passphrasePhase(): VaultPhase = keys.phase()

    override fun preparePassphrase() {
        if (prepared) {
            return
        }
        prepared = true
        if (keys.phase() == VaultPhase.SETUP) {
            memoryBlocked = !keys.allocationAvailable()
            if (!memoryBlocked) {
                keys.probeWrappingKey()
            }
        }
    }

    override fun setupHardwareWarning(): Boolean = keys.setupHardwareWarning()

    override fun settingsHardwareWarning(): Boolean = keys.showHardwareWarning()

    override fun memoryRefused(): Boolean = memoryBlocked

    override fun acceptPassphrase(passphrase: String): PassphraseStep {
        val outcome = keys.acceptNew(passphrase, hardwareAccepted = true)
        if (outcome !is VaultOutcome.Created) {
            return PassphraseStep(outcomeMessage(outcome), vaultReady = false)
        }
        val pending = heldShare
        heldShare = null
        if (pending == null) {
            return PassphraseStep("", vaultReady = true)
        }
        val now = Instant.now().toEpochMilli()
        val today =
            Instant
                .now()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .toString()
        val decision = decide(pending, now, today)
        if (decision is ShareDecision.Store) {
            store.save(decision.item)
        }
        return PassphraseStep(SAVED_TO_LAGOON, vaultReady = true)
    }

    override fun unlockPassphrase(passphrase: String): PassphraseStep {
        val outcome = keys.unlock(passphrase)
        val ready = outcome is VaultOutcome.Recreated
        return PassphraseStep(outcomeMessage(outcome), vaultReady = ready)
    }

    override fun declinePassphrase(): String {
        keys.decline()
        val pending = heldShare
        heldShare = null
        prepared = false
        return if (pending != null) NOTHING_SAVED else NO_VAULT_CREATED
    }

    override fun newEmptyVault(): String {
        keys.newEmptyVault()
        return FORGOTTEN_SENTENCE
    }

    private fun decide(
        input: ShareInput,
        now: Long,
        today: String,
    ): ShareDecision {
        val ids = ItemIds(store.usedIds())
        return decideShare(input, ids, now, today) { UUID.randomUUID().toString() }
    }
}
