package com.example.cftbin.domain.usecase

import com.example.cftbin.domain.CardRepository
import com.example.cftbin.domain.entity.CardInfo

class GetCardInfoByBinUseCase(
    private val repository: CardRepository
) {
   suspend fun getCardInfoByBin(bin: String?): CardInfo? {
       return repository.getCardInfoByBin(bin)
   }
}