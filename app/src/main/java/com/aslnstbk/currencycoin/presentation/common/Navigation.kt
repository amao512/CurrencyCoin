package com.aslnstbk.currencycoin.presentation.common

sealed class Navigation(val route: String) {
    object CoinList : Navigation(route = "coins")
    object CoinDetail : Navigation(route = "coin_detail")
}
