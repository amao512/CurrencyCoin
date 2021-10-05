package com.aslnstbk.currencycoin.data.db

import android.app.Application
import com.aslnstbk.currencycoin.data.models.FavoriteCoinEntity

class FavoriteCoinsDataSource(application: Application) {

    private val favoriteCoinsDao: FavoriteCoinDao

    init {
        val db = FavoriteCoinsDatabase.getInstance(application)

        favoriteCoinsDao = db.favoriteCoinsDao()
    }

    suspend fun getFavoriteCoins(): List<FavoriteCoinEntity> {
        return favoriteCoinsDao.getFavoriteCoins()
    }

    suspend fun getFavoriteCoinById(coinId: String): FavoriteCoinEntity? {
        return favoriteCoinsDao.getCoinById(coinId = coinId).firstOrNull()
    }

    suspend fun addFavoriteIcon(item: FavoriteCoinEntity) {
        favoriteCoinsDao.insertFavoriteIcon(coin = item)
    }

    suspend fun removeFavoriteIcon(coinId: String) {
        favoriteCoinsDao.removeFavoriteIcon(coinId)
    }
}