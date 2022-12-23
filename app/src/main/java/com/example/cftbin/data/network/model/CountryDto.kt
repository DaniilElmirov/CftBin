package com.example.cftbin.data.network.model

import com.squareup.moshi.Json

data class CountryDto(
    val numeric: String,
    @Json(name = "alpha2") val alphaTwo: String,
    val name: String,
    val emoji: String,
    val currency: String,
    val latitude: Int,
    val longitude: Int,
)
