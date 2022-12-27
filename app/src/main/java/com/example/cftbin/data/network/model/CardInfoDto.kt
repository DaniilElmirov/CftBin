package com.example.cftbin.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardInfoDto(
    val number: NumberDto,
    val scheme: String,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryDto?,
    val bank: BankDto?,
)
