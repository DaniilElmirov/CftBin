package com.example.cftbin.domain

import com.example.cftbin.domain.entity.BinItem
import com.example.cftbin.domain.entity.CardInfo
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun getCardInfoByBin(bin: String): CardInfo?

    fun getBinHistoryArray(): Flow<Set<BinItem>>

    suspend fun clearOldEntities()

    suspend fun addBinHistoryItem(binItem: BinItem)
}