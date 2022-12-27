package com.example.cftbin.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cftbin.data.CardRepositoryImpl
import com.example.cftbin.domain.entity.BinItem
import com.example.cftbin.domain.entity.CardInfo
import com.example.cftbin.domain.usecase.AddBinItemInDbUseCase
import com.example.cftbin.domain.usecase.ClearOldDbEntitiesUseCase
import com.example.cftbin.domain.usecase.GetBinItemSetUseCase
import com.example.cftbin.domain.usecase.GetCardInfoByBinUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CardRepositoryImpl(application)

    private val getCardInfoByBinUseCase = GetCardInfoByBinUseCase(repository)
    private val getBinItemSetUseCase = GetBinItemSetUseCase(repository)
    private val addBinItemInDbUseCase = AddBinItemInDbUseCase(repository)
    private val clearOldDbEntitiesUseCase = ClearOldDbEntitiesUseCase(repository)

    private val binList = getBinItemSetUseCase.getBinItemSet()

    private val _itemCardInfo: MutableStateFlow<CardInfo?> = MutableStateFlow(null)
    val itemCardInfo: StateFlow<CardInfo?>
        get() = _itemCardInfo.asStateFlow()

    private val _setBinHistory: MutableStateFlow<Set<String>> = MutableStateFlow(emptySet())
    val setBinHistory: StateFlow<Set<String>>
        get() = _setBinHistory.asStateFlow()

    init {
        viewModelScope.launch {
            binList.collect {
                it.map { binItem ->
                    _setBinHistory.value += binItem.bin
                }
            }
        }
    }

    fun getCardInfoByBin(bin: String?) {
        viewModelScope.launch {
            _itemCardInfo.value = getCardInfoByBinUseCase.getCardInfoByBin(parseBin(bin))
            val binItem = BinItem(bin = parseBin(bin))
            addBinItemInDbUseCase.addBinItemInDb(binItem)
            clearOldDbEntitiesUseCase.clearOldDbEntities()
        }
    }

    private fun parseBin(bin: String?) = bin?.trim() ?: ""
}