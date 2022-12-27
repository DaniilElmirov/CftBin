package com.example.cftbin.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BankDto(
    val name: String,
    val url: String,
    val phone: String?,
    val city: String?,
)
