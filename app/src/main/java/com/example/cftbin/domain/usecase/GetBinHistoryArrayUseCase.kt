package com.example.cftbin.domain.usecase

import com.example.cftbin.data.database.BinHistoryItemDbModel
import com.example.cftbin.domain.CardRepository
import kotlinx.coroutines.flow.Flow

class GetBinHistoryArrayUseCase(
    private val repository: CardRepository
) {
    fun getBinHistoryArray(): Flow<Array<BinHistoryItemDbModel>> {
        return repository.getBinHistoryArray()
    }
}