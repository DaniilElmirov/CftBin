package com.example.cftbin.domain

import com.example.cftbin.domain.entity.BinItem
import com.example.cftbin.domain.entity.CardInfo
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun getCardInfoByBin(bin: String): CardInfo?

    fun getBinItemSet(): Flow<Set<BinItem>>

    suspend fun deleteDuplicateDbEntities(bin: String)

    suspend fun clearOldDbEntities()

    suspend fun addBinItemInDb(binItem: BinItem)
}