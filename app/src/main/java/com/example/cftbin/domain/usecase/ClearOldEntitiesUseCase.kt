package com.example.cftbin.domain.usecase

import com.example.cftbin.domain.CardRepository

class ClearOldEntitiesUseCase(
    private val repository: CardRepository
) {
    suspend fun clearOldEntities() {
        repository.clearOldEntities()
    }
}