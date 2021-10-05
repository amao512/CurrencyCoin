package com.aslnstbk.currencycoin.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslnstbk.currencycoin.domain.models.CoinDetail
import com.aslnstbk.currencycoin.domain.usecases.AddFavoriteCoinUseCase
import com.aslnstbk.currencycoin.domain.usecases.GetCoinByIdUseCase
import com.aslnstbk.currencycoin.domain.usecases.GetFavoriteCoinByIdUseCase
import com.aslnstbk.currencycoin.domain.usecases.RemoveFavoriteCoinUseCase
import com.aslnstbk.currencycoin.presentation.common.Constants.PARAM_COIN_ID
import com.aslnstbk.currencycoin.presentation.models.ResponseData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    private val addFavoriteCoinUseCase: AddFavoriteCoinUseCase,
    private val getFavoriteCoinByIdUseCase: GetFavoriteCoinByIdUseCase,
    private val removeFavoriteCoinUseCase: RemoveFavoriteCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.IO) {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> get() = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let {
            getCoinById(coinId = it)
        }
    }

    fun onFavoriteButtonClick(coin: CoinDetail?) {
        launch {
            coin?.let {
                if (it.isFavorite) {
                    removeFavoriteCoinUseCase(coinId = it.id)
                } else {
                    addFavoriteCoinUseCase(coin = it)
                }
            }
        }
    }

    private fun getCoinById(coinId: String) {
        getCoinByIdUseCase(coinId).onEach { result ->
            when (result) {
                is ResponseData.Success -> {
                    val favoriteCoin = getFavoriteCoinByIdUseCase(coinId = result.data.id)

                    result.data.isFavorite = favoriteCoin != null

                    _state.value = CoinDetailState(coin = result.data)
                }
                is ResponseData.Error -> {
                    _state.value = CoinDetailState(error = result.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}