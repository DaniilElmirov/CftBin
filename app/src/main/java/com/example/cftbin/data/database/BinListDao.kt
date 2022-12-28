package com.example.cftbin.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BinListDao {
    @Query("SELECT * FROM bin_history_items ORDER BY id DESC LIMIT 5")
    fun getBinDbList(): Flow<List<BinItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBinItemInDb(binItem: BinItemDbModel)

    @Query("DELETE FROM bin_history_items WHERE id <= ((SELECT MAX (id) FROM bin_history_items) - 5) OR 0")
    suspend fun clearOldDbEntities()

    @Query("DELETE FROM bin_history_items WHERE bin=:bin")
    suspend fun deleteDuplicateDbEntities(bin: String)
}