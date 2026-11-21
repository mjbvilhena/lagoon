package com.mjbvilhena.lagoon.data.item

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: List<PersonalNoteEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTags(tags: List<ItemTagEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAttachments(attachments: List<AttachmentEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTombstone(tombstone: TombstoneEntity)

    @Query("DELETE FROM personal_note WHERE itemId = :itemId")
    fun deleteNotes(itemId: String)

    @Query("DELETE FROM item_tag WHERE itemId = :itemId")
    fun deleteTags(itemId: String)

    @Query("DELETE FROM attachment WHERE itemId = :itemId")
    fun deleteAttachments(itemId: String)

    @Transaction
    @Query("SELECT * FROM item WHERE id = :id")
    fun item(id: String): ItemWithChildren?

    @Transaction
    @Query("SELECT * FROM item")
    fun all(): List<ItemWithChildren>

    @Query("SELECT id FROM item UNION SELECT itemId FROM tombstone")
    fun usedIds(): List<String>

    @Query("SELECT * FROM tombstone WHERE itemId = :itemId")
    fun tombstone(itemId: String): TombstoneEntity?
}
