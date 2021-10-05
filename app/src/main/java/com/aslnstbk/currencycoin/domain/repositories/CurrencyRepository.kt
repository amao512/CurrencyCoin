package com.aslnstbk.currencycoin.domain.repositories

import com.aslnstbk.currencycoin.data.models.CoinDTO
import com.aslnstbk.currencycoin.data.models.CoinDetailDTO
import com.aslnstbk.currencycoin.data.models.FavoriteCoinEntity

interface CurrencyRepository {

    suspend fun getCoins(): List<CoinDTO>

    suspend fun getCoinById(coinId: String): CoinDetailDTO

    suspend fun getFavoriteCoins(): List<FavoriteCoinEntity>

    suspend fun addFavoriteIcon(coin: FavoriteCoinEntity)

    suspend fun getFavoriteCoinById(coinId: String): FavoriteCoinEntity?

    suspend fun removeFavoriteIcon(coinId: String)
}