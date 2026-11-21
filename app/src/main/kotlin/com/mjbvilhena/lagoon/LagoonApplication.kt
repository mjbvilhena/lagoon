package com.mjbvilhena.lagoon

import android.app.Application
import com.mjbvilhena.lagoon.data.item.ItemStore
import com.mjbvilhena.lagoon.data.item.openInMemoryVault
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
import com.mjbvilhena.lagoon.ui.VaultHost
import com.mjbvilhena.lagoon.ui.VaultSession
import java.util.UUID

class LagoonApplication :
    Application(),
    VaultHost {
    override val vault: VaultSession by lazy {
        RoomVaultSession(ItemStore(openInMemoryVault(this)))
    }
}

private class RoomVaultSession(
    private val store: ItemStore,
) : VaultSession {
    private val pending = linkedSetOf<String>()

    override fun acceptShare(
        input: ShareInput,
        now: Long,
        today: String,
    ): String {
        val ids = ItemIds(store.usedIds())
        return when (val decision = decideShare(input, ids, now, today) { UUID.randomUUID().toString() }) {
            is ShareDecision.Refuse -> NOTHING_SAVED
            is ShareDecision.Store -> {
                store.save(decision.item)
                SAVED_TO_LAGOON
            }
        }
    }

    override fun home(): List<Item> = homeItems(store.all()).filter { it.id.value !in pending }

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
        pending.add(id)
    }

    override fun undoDelete(id: String) {
        pending.remove(id)
    }

    override fun confirmDelete(now: Long) {
        pending.toList().forEach { id ->
            val item = store.item(ItemId(id)) ?: return@forEach
            store.save(moveToTrash(item, now))
        }
        pending.clear()
    }
}
