package com.aslnstbk.currencycoin.data.models

import com.aslnstbk.currencycoin.domain.models.CoinDetail
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CoinDetailDTO(
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("symbol")
    val symbol: String?,
    @JsonProperty("rank")
    val rank: Int?,
    @JsonProperty("is_active")
    val isActive: Boolean,
    @JsonProperty("tags")
    val tags: List<CoinTagDTO>?,
    @JsonProperty("team")
    val team: List<CoinTeamDTO>?,
    @JsonProperty("description")
    val description: String?
)

fun CoinDetailDTO.toCoinDetail(): CoinDetail {
    return CoinDetail(
        id = id.orEmpty(),
        name = name.orEmpty(),
        symbol = symbol.orEmpty(),
        rank = rank ?: 0,
        isActive = isActive,
        tags = tags?.map { it.toCoinTag() } ?: emptyList(),
        team = team?.map { it.toCoinTeam() } ?: emptyList(),
        description = description.orEmpty()
    )
}
