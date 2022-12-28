package com.example.cftbin.domain.usecase

import com.example.cftbin.domain.CardRepository

class DeleteDuplicateDbEntitiesUseCase(
    private val repository: CardRepository
) {
    suspend fun deleteDuplicateDbEntities(bin: String) {
        repository.deleteDuplicateDbEntities(bin)
    }
}