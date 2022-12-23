package com.example.cftbin.domain

import com.example.cftbin.domain.entity.CardInfo

interface CardRepository {
    suspend fun getCardInfoByBin(bin: String?): CardInfo?
}