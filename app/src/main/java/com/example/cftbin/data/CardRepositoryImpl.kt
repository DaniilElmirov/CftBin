package com.example.cftbin.data

import android.app.Application
import android.util.Log
import com.example.cftbin.data.database.BinItemDatabase
import com.example.cftbin.data.mapper.CardMapper
import com.example.cftbin.data.network.CardApi
import com.example.cftbin.domain.CardRepository
import com.example.cftbin.domain.entity.BinItem
import com.example.cftbin.domain.entity.CardInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://lookup.binlist.net/"

class CardRepositoryImpl(application: Application) : CardRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    private val api = retrofit.create(CardApi::class.java)

    private val mapper = CardMapper()

    private val binListDao = BinItemDatabase.getInstance(application).binListDao()

    override suspend fun getCardInfoByBin(bin: String): CardInfo? {
        var cardInfo: CardInfo? = null

        try {
            val response = api.getCardInfoByBin(bin)
            cardInfo = mapper.mapCardInfoDtoToEntity(response)
        } catch (e: Exception) {
            Log.d("TAG2", "Catch")
        }
        return cardInfo
    }

    override fun getBinItemSet(): Flow<Set<BinItem>> {
        return binListDao.getBinDbList().map {
            mapper.mapListBinDbToSetItem(it)
        }
    }

    override suspend fun deleteDuplicateDbEntities(bin: String) {
        binListDao.deleteDuplicateDbEntities(bin)
    }

    override suspend fun clearOldDbEntities() {
        binListDao.clearOldDbEntities()
    }

    override suspend fun addBinItemInDb(binItem: BinItem) {
        if (binItem.bin != "") {
            binListDao.addBinItemInDb(mapper.mapBinItemToDbModel(binItem))
        }
    }
}