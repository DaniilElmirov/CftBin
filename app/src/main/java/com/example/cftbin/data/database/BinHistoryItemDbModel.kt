package com.example.cftbin.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_history_items")
data class BinHistoryItemDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val bin: String,
)
