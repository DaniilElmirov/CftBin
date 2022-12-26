package com.example.cftbin.domain

import com.example.cftbin.data.database.BinHistoryItemDbModel
import com.example.cftbin.domain.entity.CardInfo
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun getCardInfoByBin(bin: String): CardInfo?

    fun getBinHistoryArray(): Flow<Array<BinHistoryItemDbModel>>

    fun clearOldEntities()
}