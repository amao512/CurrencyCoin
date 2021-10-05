package com.aslnstbk.currencycoin.presentation.favorite_coins

import com.aslnstbk.currencycoin.domain.models.Coin

data class FavoriteCoinsState(
    val favoriteCoins: List<Coin>,
    val error: String = ""
)
