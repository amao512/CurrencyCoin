package com.aslnstbk.currencycoin.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aslnstbk.currencycoin.domain.models.Coin
import com.aslnstbk.currencycoin.domain.models.CoinDetail

@Entity(tableName = "favorite_coins")
data class FavoriteCoinEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "symbol")
    val symbol: String? = null,
    @ColumnInfo(name = "rank")
    val rank: Int? = null,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean = false,
    @ColumnInfo(name = "description")
    val description: String? = null
)

fun FavoriteCoinEntity.toCoin(): Coin {
    return Coin(
        id = id,
        name = name.orEmpty(),
        symbol = symbol.orEmpty(),
        rank = rank ?: 0,
        isActive = isActive
    )
}

fun FavoriteCoinEntity?.fromNullableToCoin(): Coin {
    return Coin(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
        symbol = this?.symbol.orEmpty(),
        rank = this?.rank ?: 0,
        isActive = this?.isActive ?: false
    )
}

fun CoinDetail.toFavoriteCoinEntity(): FavoriteCoinEntity {
    return FavoriteCoinEntity(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        description = description
    )
}