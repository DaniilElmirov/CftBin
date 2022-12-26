package com.example.cftbin.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BinListDao {
    @Query("SELECT * FROM bin_history_items ORDER BY id DESC LIMIT 5")
    fun getBinHistoryArray(): Flow<Array<BinHistoryItemDbModel>>

    @Query("DELETE FROM bin_history_items WHERE id < ((SELECT MAX (id)) - 5)")
    fun clearOldEntities()
}