package com.example.cftbin.domain.usecase

import com.example.cftbin.domain.CardRepository
import com.example.cftbin.domain.entity.BinItem
import kotlinx.coroutines.flow.Flow

class GetBinItemSetUseCase(
    private val repository: CardRepository
) {
    fun getBinItemSet(): Flow<Set<BinItem>> {
        return repository.getBinItemSet()
    }
}