package com.aslnstbk.currencycoin.data.db

import androidx.room.*
import com.aslnstbk.currencycoin.data.models.FavoriteCoinEntity

@Dao
interface FavoriteCoinDao {

    @Query("SELECT * FROM favorite_coins")
    suspend fun getFavoriteCoins(): List<FavoriteCoinEntity>

    @Query("SELECT * FROM favorite_coins WHERE id = :coinId")
    suspend fun getCoinById(coinId: String): List<FavoriteCoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteIcon(coin: FavoriteCoinEntity)

    @Query("DELETE FROM favorite_coins WHERE id = :coinId")
    suspend fun removeFavoriteIcon(coinId: String)
}