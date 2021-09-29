package com.aslnstbk.currencycoin.data.api

import com.aslnstbk.currencycoin.data.models.CoinDTO
import com.aslnstbk.currencycoin.data.models.CoinDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {

    @GET("v1/coins")
    suspend fun getCoins(): List<CoinDTO>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDTO
}