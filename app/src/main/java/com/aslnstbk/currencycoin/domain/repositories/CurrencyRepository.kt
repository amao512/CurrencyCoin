package com.aslnstbk.currencycoin.domain.repositories

import com.aslnstbk.currencycoin.data.models.CoinDTO
import com.aslnstbk.currencycoin.data.models.CoinDetailDTO

interface CurrencyRepository {

    suspend fun getCoins(): List<CoinDTO>

    suspend fun getCoinById(coinId: String): CoinDetailDTO
}