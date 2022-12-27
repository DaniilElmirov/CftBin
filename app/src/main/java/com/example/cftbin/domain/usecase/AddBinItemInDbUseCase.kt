package com.example.cftbin.domain.usecase

import com.example.cftbin.domain.CardRepository
import com.example.cftbin.domain.entity.BinItem

class AddBinItemInDbUseCase(
    private val repository: CardRepository
) {
    suspend fun addBinItemInDb(binItem: BinItem) {
        repository.addBinItemInDb(binItem)
    }
}