package com.aslnstbk.currencycoin.presentation.coin_detail

import com.aslnstbk.currencycoin.domain.models.CoinDetail

data class CoinDetailState(
    val coin: CoinDetail? = null,
    val error: String = ""
)
