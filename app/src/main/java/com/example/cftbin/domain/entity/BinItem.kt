package com.example.cftbin.domain.entity

data class BinItem(
    var id: Int = UNDEFINED_ID,
    val bin: String,
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}