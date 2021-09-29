package com.aslnstbk.currencycoin.domain.models

data class CoinDetail(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<CoinTag>,
    val team: List<CoinTeam>,
    val description: String
)
