package com.aslnstbk.currencycoin.data.models

import com.aslnstbk.currencycoin.domain.models.CoinTeam
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CoinTeamDTO(
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("position")
    val position: String?
)

fun CoinTeamDTO.toCoinTeam(): CoinTeam {
    return CoinTeam(
        id = id.orEmpty(),
        name = name.orEmpty(),
        position = position.orEmpty()
    )
}