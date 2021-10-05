package com.aslnstbk.currencycoin.presentation.favorite_coins

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslnstbk.currencycoin.domain.usecases.GetFavoriteCoinsUseCase
import com.aslnstbk.currencycoin.presentation.models.ResponseData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteCoinsViewModel @Inject constructor(
    private val getFavoriteCoinsUseCase: GetFavoriteCoinsUseCase
) : ViewModel() {

    private val _favoriteCoins = mutableStateOf(FavoriteCoinsState(emptyList()))
    val favoriteCoins: MutableState<FavoriteCoinsState>
        get() = _favoriteCoins

    init {
        getFavoriteCoins()
    }

    private fun getFavoriteCoins() {
        getFavoriteCoinsUseCase().onEach {
            when (it) {
                is ResponseData.Success -> {
                    _favoriteCoins.value = FavoriteCoinsState(favoriteCoins = it.data)
                }
                is ResponseData.Error -> {
                    favoriteCoins.value = FavoriteCoinsState(
                        favoriteCoins = emptyList(),
                        error = it.error
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}