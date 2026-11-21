package com.mjbvilhena.lagoon.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.mjbvilhena.lagoon.domain.item.EditResult
import com.mjbvilhena.lagoon.domain.item.IN_TRASH
import com.mjbvilhena.lagoon.domain.item.Item
import com.mjbvilhena.lagoon.domain.item.ItemType
import com.mjbvilhena.lagoon.domain.item.NOTHING_SAVED_YET
import com.mjbvilhena.lagoon.domain.item.PersonalNote
import com.mjbvilhena.lagoon.domain.item.TITLE_REQUIRED
import com.mjbvilhena.lagoon.domain.item.UNDO
import com.mjbvilhena.lagoon.domain.item.addNote
import java.time.Instant
import java.time.ZoneId

/** Home, detail, and edit. It does not request permissions. */
class LauncherActivity : Activity() {
    private val handler = Handler(Looper.getMainLooper())
    private var screen = Screen.Home
    private var itemId: String? = null
    private var pendingDelete: String? = null
    private val confirmDelete =
        Runnable {
            finishPendingDelete()
            if (screen == Screen.Home) {
                showHome()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ReleaseLogProbe.touch()
        showHome()
    }

    override fun onStop() {
        if (pendingDelete != null) {
            handler.removeCallbacks(confirmDelete)
            finishPendingDelete()
        }
        super.onStop()
    }

    private fun finishPendingDelete() {
        vault().confirmDelete(Instant.now().toEpochMilli())
        pendingDelete = null
    }

    private fun showHome() {
        screen = Screen.Home
        val items = vault().home()
        val column = column()
        if (items.isEmpty()) {
            column.addView(label(NOTHING_SAVED_YET))
        }
        items.forEach { item -> column.addView(row(item)) }
        if (pendingDelete != null) {
            column.addView(snackbar())
        }
        setContentView(scroll(column))
    }

    private fun showDetail(id: String) {
        if (pendingDelete != null) {
            handler.removeCallbacks(confirmDelete)
            finishPendingDelete()
        }
        screen = Screen.Detail
        itemId = id
        val content = vault().detail(id) ?: return showHome()
        val column = column()
        if (content.shown) {
            column.addView(label(content.title, content.title))
            if (content.body.isNotEmpty()) {
                column.addView(label(content.body))
            }
            content.notes.forEach { note ->
                column.addView(label(note.writtenOn + "\n" + note.text))
            }
            if (content.tags.isNotEmpty()) {
                column.addView(label(content.tags.joinToString(", ")))
            }
            content.attachments.forEach { attachment ->
                column.addView(label(attachment.displayName))
            }
            content.lastSavedAt?.let { column.addView(label(savedDate(it))) }
            content.sourceUrl?.let { url ->
                column.addView(label(url))
                column.addView(action("Open") { openUrl(url) })
            }
            listOfNotNull(content.sourceDomain, content.sendingApp).forEach { source ->
                column.addView(label(source))
            }
            content.ocrText?.let { text ->
                column.addView(selectable(text))
            }
            content.snapshotText?.let { column.addView(label(it)) }
            content.reminder?.let { column.addView(label("Reminder")) }
            content.expiry?.let { column.addView(label("Expiry")) }
            if (content.foggyPreview) {
                column.addView(
                    View(this).apply {
                        contentDescription = "Preview"
                        minimumHeight = 48
                    },
                )
            }
            column.addView(action("Edit") { showEdit(id) })
            column.addView(action("Delete") { delete(id) })
        }
        setContentView(scroll(column))
    }

    private fun showEdit(id: String) {
        screen = Screen.Edit
        val item = vault().item(id) ?: return showHome()
        val column = column()
        val title = field(item.title)
        val body = field(item.body)
        val noteFields = item.notes.map { note -> note to field(note.text) }
        val added = field("")
        val tags = field(item.tags.joinToString(", "))
        column.addView(title)
        column.addView(body)
        noteFields.forEach { (_, field) -> column.addView(field) }
        column.addView(added)
        column.addView(tags)
        val error = label("")
        column.addView(error)
        column.addView(
            action("Save") {
                val today =
                    Instant
                        .now()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                        .toString()
                val notes = notesFrom(item.notes, noteFields, added.text.toString(), today)
                when (
                    vault().edit(
                        id,
                        title.text.toString(),
                        body.text.toString(),
                        notes,
                        tags.text
                            .toString()
                            .split(',')
                            .map { it.trim() }
                            .filter { it.isNotEmpty() },
                        Instant.now().toEpochMilli(),
                    )
                ) {
                    is EditResult.Saved -> showDetail(id)
                    EditResult.TitleRequired -> {
                        error.text = TITLE_REQUIRED
                        error.contentDescription = TITLE_REQUIRED
                    }
                }
            },
        )
        setContentView(scroll(column))
    }

    private fun delete(id: String) {
        vault().beginDelete(id)
        pendingDelete = id
        handler.removeCallbacks(confirmDelete)
        handler.postDelayed(confirmDelete, 8_000L)
        showHome()
    }

    private fun snackbar(): LinearLayout {
        val bar = rowLayout()
        bar.addView(label(IN_TRASH))
        bar.addView(
            action(UNDO) {
                pendingDelete?.let { vault().undoDelete(it) }
                pendingDelete = null
                handler.removeCallbacks(confirmDelete)
                showHome()
            },
        )
        return bar
    }

    private fun row(item: Item): Button {
        val sensitive = item.sensitive
        val title = if (sensitive) "Sensitive" else item.title
        val meta = listOfNotNull(typeName(item.type), savedDate(item.lastSavedAt), sourceOf(item)).joinToString(" · ")
        val marker =
            when {
                sensitive -> null
                item.reminder != null -> "Reminder"
                item.expiry != null -> "Expiry"
                else -> null
            }
        val visible = listOfNotNull(title, meta, marker).joinToString("\n")
        return action(visible) { showDetail(item.id.value) }.apply {
            contentDescription = if (sensitive) "Sensitive" else item.title
        }
    }

    private fun notesFrom(
        existing: List<PersonalNote>,
        fields: List<Pair<PersonalNote, EditText>>,
        added: String,
        today: String,
    ): List<PersonalNote> {
        val edited =
            fields.map { (note, field) ->
                note.copy(text = field.text.toString())
            }
        val extra = added.trim()
        return if (extra.isEmpty()) edited else addNote(edited, "note-$today-$extra", today, extra)
    }

    private fun openUrl(url: String) {
        val uri = Uri.parse(url)
        if (uri.scheme != "http" && uri.scheme != "https") {
            return
        }
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun vault(): VaultSession = (application as VaultHost).vault

    private fun scroll(child: View): ScrollView = ScrollView(this).apply { addView(child) }

    private fun column(): LinearLayout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }

    private fun rowLayout(): LinearLayout = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }

    private fun label(
        text: String,
        name: String = text,
    ): TextView =
        TextView(this).apply {
            this.text = text
            contentDescription = name
        }

    private fun selectable(text: String): TextView = label(text).apply { setTextIsSelectable(true) }

    private fun field(value: String): EditText = EditText(this).apply { setText(value) }

    private fun action(
        name: String,
        onClick: () -> Unit,
    ): Button =
        Button(this).apply {
            text = name
            contentDescription = name
            setOnClickListener { onClick() }
        }

    private fun savedDate(epochMillis: Long): String =
        Instant
            .ofEpochMilli(epochMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .toString()

    private fun sourceOf(item: Item): String? =
        listOfNotNull(item.sourceDomain, item.sendingApp)
            .takeIf {
                it.isNotEmpty()
            }?.joinToString(" · ")

    private fun typeName(type: ItemType): String =
        when (type) {
            ItemType.LINK -> "Link"
            ItemType.NOTE -> "Note"
            ItemType.IMAGE -> "Image"
            ItemType.PDF -> "PDF"
        }

    private enum class Screen { Home, Detail, Edit }
}
