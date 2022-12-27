package com.example.cftbin.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NumberDto(
    val length: Int,
    val luhn: Boolean,
)
