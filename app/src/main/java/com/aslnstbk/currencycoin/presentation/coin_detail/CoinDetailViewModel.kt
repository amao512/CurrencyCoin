package com.aslnstbk.currencycoin.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslnstbk.currencycoin.domain.usecases.GetCoinByIdUseCase
import com.aslnstbk.currencycoin.presentation.common.Constants.PARAM_COIN_ID
import com.aslnstbk.currencycoin.presentation.models.ResponseData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> get() = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let {
            getCoinById(coinId = it)
        }
    }

    private fun getCoinById(coinId: String) {
        getCoinByIdUseCase(coinId).onEach { result ->
            when (result) {
                is ResponseData.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is ResponseData.Error -> {
                    _state.value = CoinDetailState(error = result.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}