package com.example.cftbin.data.network

import com.example.cftbin.data.network.model.CardInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CardApi {
    @GET("{bin}")
    suspend fun getCardInfoByBin(@Path("bin") bin: String): CardInfoDto
}