package com.example.cftbin.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cftbin.data.CardRepositoryImpl
import com.example.cftbin.domain.entity.BinItem
import com.example.cftbin.domain.entity.CardInfo
import com.example.cftbin.domain.usecase.AddBinHistoryItemUseCase
import com.example.cftbin.domain.usecase.ClearOldEntitiesUseCase
import com.example.cftbin.domain.usecase.GetBinHistoryArrayUseCase
import com.example.cftbin.domain.usecase.GetCardInfoByBinUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CardRepositoryImpl(application)

    private val getCardInfoByBinUseCase = GetCardInfoByBinUseCase(repository)
    private val getBinHistoryArrayUseCase = GetBinHistoryArrayUseCase(repository)
    private val addBinHistoryItemUseCase = AddBinHistoryItemUseCase(repository)
    private val clearOldEntitiesUseCase = ClearOldEntitiesUseCase(repository)

    val binArray = getBinHistoryArrayUseCase.getBinHistoryArray()

    private val _itemCardInfo: MutableStateFlow<CardInfo?> = MutableStateFlow(null)
    val itemCardInfo: StateFlow<CardInfo?>
        get() = _itemCardInfo.asStateFlow()

    fun getCardInfoByBin(bin: String?) {
        viewModelScope.launch {
           _itemCardInfo.value = getCardInfoByBinUseCase.getCardInfoByBin(parseBin(bin))
            val binItem = BinItem(bin = parseBin(bin))
            addBinHistoryItemUseCase.addBinHistoryItem(binItem)
        }
    }

    suspend fun clearOldEntities() {
        clearOldEntitiesUseCase.clearOldEntities()
    }

    private fun parseBin(bin: String?) = bin?.trim() ?: ""
}