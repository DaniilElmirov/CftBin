package com.example.cftbin.domain.usecase

import com.example.cftbin.domain.CardRepository
import com.example.cftbin.domain.entity.BinItem
import kotlinx.coroutines.flow.Flow

class GetBinHistoryArrayUseCase(
    private val repository: CardRepository
) {
    fun getBinHistoryArray(): Flow<Set<BinItem>> {
        return repository.getBinHistoryArray()
    }
}