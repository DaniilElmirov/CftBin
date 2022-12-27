package com.example.cftbin.data.mapper

import com.example.cftbin.data.database.BinItemDbModel
import com.example.cftbin.data.network.model.BankDto
import com.example.cftbin.data.network.model.CardInfoDto
import com.example.cftbin.data.network.model.CountryDto
import com.example.cftbin.data.network.model.NumberDto
import com.example.cftbin.domain.entity.*
import com.example.cftbin.domain.entity.Number

class CardMapper {
    private fun mapBankDtoToEntity(bankDto: BankDto?) = bankDto?.let {
        Bank(
            it.name,
            it.url,
            it.phone,
            it.city,
        )
    }

    private fun mapCountryDtoToEntity(countryDto: CountryDto?) = countryDto?.let {
        Country(
            it.numeric,
            it.alphaTwo,
            it.name,
            it.emoji,
            it.currency,
            it.latitude,
            it.longitude,
        )
    }

    private fun mapNumberDtoToEntity(numberDto: NumberDto) = Number(
        length = numberDto.length,
        luhn = numberDto.luhn,
    )

    fun mapCardInfoDtoToEntity(cardInfoDto: CardInfoDto) = CardInfo(
        number = mapNumberDtoToEntity(cardInfoDto.number),
        scheme = cardInfoDto.scheme,
        type = cardInfoDto.type,
        brand = cardInfoDto.brand,
        prepaid = cardInfoDto.prepaid,
        country = mapCountryDtoToEntity(cardInfoDto.country),
        bank = mapBankDtoToEntity(cardInfoDto.bank),
    )

    fun mapBinItemToDbModel(binItem: BinItem) = BinItemDbModel(id = binItem.id, bin = binItem.bin)

    private fun mapBinDbModelToItem(binItem: BinItemDbModel) = BinItem(id = binItem.id, bin = binItem.bin)

    fun mapDbBinArrayToArrayEntity(arrayDb: Array<BinItemDbModel>) = arrayDb.map {
        mapBinDbModelToItem(it)
    }.toSet()
}