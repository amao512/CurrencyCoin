package com.aslnstbk.currencycoin.data.repositories

import com.aslnstbk.currencycoin.data.api.CurrencyApi
import com.aslnstbk.currencycoin.data.db.FavoriteCoinsDataSource
import com.aslnstbk.currencycoin.data.models.CoinDTO
import com.aslnstbk.currencycoin.data.models.CoinDetailDTO
import com.aslnstbk.currencycoin.data.models.FavoriteCoinEntity
import com.aslnstbk.currencycoin.domain.repositories.CurrencyRepository
import javax.inject.Inject

class DefaultCurrencyRepository @Inject constructor(
    private val api: CurrencyApi,
    private val dataSource: FavoriteCoinsDataSource
) : CurrencyRepository {

    override suspend fun getCoins(): List<CoinDTO> = api.getCoins()

    override suspend fun getCoinById(coinId: String): CoinDetailDTO {
        return api.getCoinById(coinId = coinId)
    }

    override suspend fun getFavoriteCoins(): List<FavoriteCoinEntity> {
        return dataSource.getFavoriteCoins()
    }

    override suspend fun addFavoriteIcon(coin: FavoriteCoinEntity) {
        dataSource.addFavoriteIcon(coin)
    }

    override suspend fun getFavoriteCoinById(coinId: String): FavoriteCoinEntity? {
        return dataSource.getFavoriteCoinById(coinId)
    }

    override suspend fun removeFavoriteIcon(coinId: String) {
        dataSource.removeFavoriteIcon(coinId)
    }
}