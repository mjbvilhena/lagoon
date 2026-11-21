package com.mjbvilhena.lagoon.data.item

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        ItemEntity::class,
        PersonalNoteEntity::class,
        ItemTagEntity::class,
        AttachmentEntity::class,
        TombstoneEntity::class,
    ],
    version = 2,
    exportSchema = false,
)
abstract class VaultDatabase : RoomDatabase() {
    abstract fun items(): ItemDao
}

/**
 * Opens an in-memory database. A file database is not opened here: vault bytes
 * stay off disk until a vault key exists.
 */
fun openInMemoryVault(context: Context): VaultDatabase =
    Room
        .inMemoryDatabaseBuilder(context, VaultDatabase::class.java)
        .addMigrations(ITEM_MIGRATION_1_2)
        .allowMainThreadQueries()
        .build()
