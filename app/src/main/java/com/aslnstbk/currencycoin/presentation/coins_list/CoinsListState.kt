package com.aslnstbk.currencycoin.presentation.coins_list

import com.aslnstbk.currencycoin.domain.models.Coin

data class CoinsListState(
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
