package com.example.cftbin.domain.usecase

import com.example.cftbin.domain.CardRepository
import com.example.cftbin.domain.entity.BinItem

class AddBinHistoryItemUseCase(
    private val repository: CardRepository
) {
    suspend fun addBinHistoryItem(binItem: BinItem) {
        repository.addBinHistoryItem(binItem)
    }
}