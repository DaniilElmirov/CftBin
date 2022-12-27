package com.example.cftbin.domain.usecase

import com.example.cftbin.domain.CardRepository

class ClearOldDbEntitiesUseCase(
    private val repository: CardRepository
) {
    suspend fun clearOldDbEntities() {
        repository.clearOldDbEntities()
    }
}