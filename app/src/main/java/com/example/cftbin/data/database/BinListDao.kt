package com.example.cftbin.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BinListDao {
    @Query("SELECT * FROM bin_history_items ORDER BY id DESC LIMIT 5")
    fun getBinHistoryArray(): Flow<Array<BinItemDbModel>>

    @Insert
    suspend fun addBinHistoryItem(binItem: BinItemDbModel)

//    @Query("DELETE FROM bin_history_items WHERE id < ((SELECT MAX (id)) - 5) OR 0")
//    suspend fun clearOldEntities()
}