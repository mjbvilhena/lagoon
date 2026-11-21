package com.mjbvilhena.lagoon.domain.share

import com.mjbvilhena.lagoon.domain.item.Item
import com.mjbvilhena.lagoon.domain.item.ItemIds
import com.mjbvilhena.lagoon.domain.item.ItemType
import com.mjbvilhena.lagoon.domain.item.PersonalNote
import com.mjbvilhena.lagoon.domain.item.createItem
import java.net.URI

const val NOTHING_SAVED = "Nothing was saved."

const val SAVED_TO_LAGOON = "Saved to Lagoon"

const val IMAGE_BYTE_CAP = 20L * 1024L * 1024L

private val URL_IN_TEXT = Regex("""https?://\S+""")

data class ShareInput(
    val mimeType: String?,
    val text: String?,
    val subject: String?,
    val streamScheme: String?,
    val streamAuthority: String?,
    val streamSizeBytes: Long?,
    val sendingApp: String?,
    val ownFileProviderAuthorities: Set<String>,
)

sealed class ShareDecision {
    data class Store(
        val item: Item,
    ) : ShareDecision()

    data object Refuse : ShareDecision()
}

fun decideShare(
    input: ShareInput,
    ids: ItemIds,
    now: Long,
    today: String,
    draw: () -> String,
): ShareDecision {
    if (input.streamScheme.equals("file", ignoreCase = true)) {
        return ShareDecision.Refuse
    }
    val authority = input.streamAuthority
    if (authority != null && input.ownFileProviderAuthorities.any { it.equals(authority, ignoreCase = true) }) {
        return ShareDecision.Refuse
    }
    val image = input.mimeType?.startsWith("image/") == true
    val size = input.streamSizeBytes
    if (image && size != null && size > IMAGE_BYTE_CAP) {
        return ShareDecision.Refuse
    }
    val text = input.text
    val url = text?.let { firstHttpUrl(it) }
    if (url != null) {
        return ShareDecision.Store(linkItem(input, url, ids, now, today, draw))
    }
    if (text != null && text.isNotBlank() && input.mimeType?.startsWith("text/") != false) {
        return ShareDecision.Store(noteItem(input, text, ids, now, draw))
    }
    return ShareDecision.Refuse
}

private fun linkItem(
    input: ShareInput,
    url: String,
    ids: ItemIds,
    now: Long,
    today: String,
    draw: () -> String,
): Item {
    val leftover = leftoverText(input.text.orEmpty(), url)
    val note =
        leftover?.let {
            PersonalNote(id = ids.allocate(draw).value, writtenOn = today, text = it)
        }
    val host = runCatching { URI(url).host }.getOrNull()
    return createItem(
        ids = ids,
        type = ItemType.LINK,
        title = linkTitle(input.subject, leftover, url),
        body = "",
        now = now,
        draw = draw,
        sourceUrl = url,
        notes = listOfNotNull(note),
        sourceDomain = host,
        sendingApp = input.sendingApp?.takeIf { it.isNotBlank() },
    )
}

private fun noteItem(
    input: ShareInput,
    text: String,
    ids: ItemIds,
    now: Long,
    draw: () -> String,
): Item {
    val subject = input.subject?.trim()?.takeIf { it.isNotEmpty() }
    val title =
        subject ?: text
            .trim()
            .lineSequence()
            .first()
            .take(120)
    return createItem(
        ids = ids,
        type = ItemType.NOTE,
        title = title,
        body = text,
        now = now,
        draw = draw,
        sendingApp = input.sendingApp?.takeIf { it.isNotBlank() },
    )
}

internal fun firstHttpUrl(text: String): String? = URL_IN_TEXT.find(text)?.value?.trimEnd('.', ',', ')', ']')

internal fun leftoverText(
    text: String,
    url: String,
): String? {
    val leftover = text.replace(url, " ").replace(Regex("\\s+"), " ").trim()
    return leftover.takeIf { it.isNotEmpty() }
}

internal fun linkTitle(
    subject: String?,
    leftover: String?,
    url: String,
): String {
    val supplied = subject?.trim()?.takeIf { it.isNotEmpty() }
    if (supplied != null) {
        return supplied
    }
    val line =
        leftover
            ?.lineSequence()
            ?.firstOrNull()
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
    if (line != null) {
        return line.take(120)
    }
    val parsed = URI(url)
    val host = parsed.host.orEmpty()
    if (isTokenLooking(url)) {
        return host
    }
    val path = parsed.path.orEmpty().trimEnd('/')
    return if (path.isEmpty()) host else host + path
}
