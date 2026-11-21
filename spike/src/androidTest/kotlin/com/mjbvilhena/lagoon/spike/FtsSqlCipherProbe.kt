package com.mjbvilhena.lagoon.spike

import android.content.Context
import net.zetetic.database.sqlcipher.SQLiteConnection
import net.zetetic.database.sqlcipher.SQLiteDatabase
import net.zetetic.database.sqlcipher.SQLiteDatabaseHook
import java.io.File

/**
 * The US-57 checks. The raw key is the ADR-0006 form
 * `PRAGMA key = "x'<64 hex>'"`. This class does not choose a product cipher.
 */
object FtsSqlCipherProbe {
    private val rightKey = ByteArray(32) { index -> index.toByte() }.toHex()
    private val wrongKey = ByteArray(32) { 0x5a }.toHex()

    fun run(context: Context): List<String> {
        System.loadLibrary("sqlcipher")
        val file = File(context.cacheDir, "fts-spike.db")
        if (file.exists() && !file.delete()) {
            error("could not replace ${file.name}")
        }
        open(file, rightKey).use { db ->
            val tempStore = db.pragma("temp_store")
            check(tempStore == "2") { "temp_store was $tempStore, expected MEMORY (2)" }
            db.execSQL(
                "CREATE VIRTUAL TABLE search USING fts5(" +
                    "title, tags, note, body, extracted, ocr, snapshot, " +
                    "tokenize = 'unicode61 remove_diacritics 2')",
            )
            db.execSQL(
                "INSERT INTO search(search, rank) VALUES('rank', 'bm25(10.0, 5.0, 3.0, 1.0, 1.0, 1.0, 1.0)')",
            )
            insert(db, "alpha", "xxxxx", "xxxxx", "xxxxx")
            insert(db, "xxxxx", "alpha", "xxxxx", "xxxxx")
            insert(db, "xxxxx", "xxxxx", "xxxxx", "alpha")
            insert(db, "café", "xxxxx", "xxxxx", "xxxxx")
            val prefix = db.longs("SELECT rowid FROM search WHERE search MATCH 'caf*'")
            check(prefix.isNotEmpty()) { "prefix caf* matched nothing" }
            val folded = db.longs("SELECT rowid FROM search WHERE search MATCH 'cafe*'")
            check(folded.isNotEmpty()) { "folded prefix cafe* matched nothing" }
            val weighted =
                "bm25(search, 10.0, 5.0, 3.0, 1.0, 1.0, 1.0, 1.0)"
            val order =
                db.texts(
                    "SELECT title || '|' || tags || '|' || body FROM search " +
                        "WHERE search MATCH 'alpha' ORDER BY $weighted",
                )
            check(order == listOf("alpha|xxxxx|xxxxx", "xxxxx|alpha|xxxxx", "xxxxx|xxxxx|alpha")) {
                "weighted order was $order"
            }
            val snippet = db.text("SELECT snippet(search, 0, '<', '>', '...', 8) FROM search WHERE title MATCH 'alpha'")
            check(snippet.contains("<alpha>")) { "snippet was $snippet" }
            val highlight = db.text("SELECT highlight(search, 0, '<', '>') FROM search WHERE title MATCH 'alpha'")
            check(highlight.contains("<alpha>")) { "highlight was $highlight" }
        }
        var wrongKeyFailed = false
        try {
            open(file, wrongKey).use { db ->
                db.longs("SELECT count(*) FROM search")
            }
        } catch (_: Exception) {
            wrongKeyFailed = true
        }
        check(wrongKeyFailed) { "the file opened with the wrong key" }
        open(file, rightKey).use { db ->
            val count = db.longs("SELECT count(*) FROM search").single()
            check(count == 4L) { "reopen count was $count" }
        }
        return listOf("pass")
    }

    private fun insert(
        db: SQLiteDatabase,
        title: String,
        tags: String,
        note: String,
        body: String,
    ) {
        db.execSQL(
            "INSERT INTO search(title, tags, note, body, extracted, ocr, snapshot) VALUES(?, ?, ?, ?, ?, ?, ?)",
            arrayOf(title, tags, note, body, "xxxxx", "xxxxx", "xxxxx"),
        )
    }

    private fun open(
        file: File,
        hexKey: String,
    ): SQLiteDatabase {
        val hook =
            object : SQLiteDatabaseHook {
                override fun preKey(connection: SQLiteConnection) {
                    connection.executeRaw("PRAGMA key = \"x'$hexKey'\"", null, null)
                }

                override fun postKey(connection: SQLiteConnection) {
                    connection.execute("PRAGMA temp_store = MEMORY", null, null)
                }
            }
        return SQLiteDatabase.openOrCreateDatabase(
            file,
            ByteArray(0),
            null,
            null,
            hook,
        )
    }
}

private fun ByteArray.toHex(): String = joinToString("") { "%02x".format(it) }

private fun SQLiteDatabase.pragma(name: String): String = text("PRAGMA $name")

private fun SQLiteDatabase.text(sql: String): String {
    rawQuery(sql, null).use { cursor ->
        check(cursor.moveToFirst()) { "no row for $sql" }
        return cursor.getString(0)
    }
}

private fun SQLiteDatabase.longs(sql: String): List<Long> {
    rawQuery(sql, null).use { cursor ->
        val values = ArrayList<Long>()
        while (cursor.moveToNext()) {
            values.add(cursor.getLong(0))
        }
        return values
    }
}

private fun SQLiteDatabase.texts(sql: String): List<String> {
    rawQuery(sql, null).use { cursor ->
        val values = ArrayList<String>()
        while (cursor.moveToNext()) {
            values.add(cursor.getString(0))
        }
        return values
    }
}
