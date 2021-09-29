package com.aslnstbk.currencycoin.presentation.coins_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslnstbk.currencycoin.domain.usecases.GetCoinsListUseCase
import com.aslnstbk.currencycoin.presentation.models.ResponseData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(
    private val getCoinsListUseCase: GetCoinsListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinsListState())
    val state: State<CoinsListState> get() = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsListUseCase().onEach { result ->
            when (result) {
                is ResponseData.Success -> {
                    _state.value = CoinsListState(coins = result.data)
                }
                is ResponseData.Error -> {
                    _state.value = CoinsListState(error = result.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}