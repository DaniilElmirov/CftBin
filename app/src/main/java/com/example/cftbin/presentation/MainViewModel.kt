package com.example.cftbin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cftbin.data.CardRepositoryImpl
import com.example.cftbin.domain.entity.CardInfo
import com.example.cftbin.domain.usecase.GetCardInfoByBinUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = CardRepositoryImpl()
    private val getCardInfoByBinUseCase = GetCardInfoByBinUseCase(repository)

    private val _itemCardInfo: MutableStateFlow<CardInfo?> = MutableStateFlow(null)
    val itemCardInfo: StateFlow<CardInfo?>
        get() = _itemCardInfo.asStateFlow()

    fun getCardInfoByBin(bin: String?) {
        viewModelScope.launch {
           _itemCardInfo.value = getCardInfoByBinUseCase.getCardInfoByBin(parseBin(bin))
        }
    }

    private fun parseBin(bin: String?) = bin?.trim() ?: ""
}