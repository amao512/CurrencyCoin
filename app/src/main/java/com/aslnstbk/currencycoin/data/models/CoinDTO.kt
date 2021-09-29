package com.aslnstbk.currencycoin.data.models

import com.aslnstbk.currencycoin.domain.models.Coin
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CoinDTO(
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("symbol")
    val symbol: String?,
    @JsonProperty("rank")
    val rank: Int?,
    @JsonProperty("is_active")
    val isActive: Boolean
)

fun CoinDTO.toCoin(): Coin {
    return Coin(
        id = id.orEmpty(),
        name = name.orEmpty(),
        symbol = symbol.orEmpty(),
        rank = rank ?: 0,
        isActive = isActive
    )
}