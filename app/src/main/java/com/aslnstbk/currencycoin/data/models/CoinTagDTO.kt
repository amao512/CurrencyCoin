package com.aslnstbk.currencycoin.data.models

import com.aslnstbk.currencycoin.domain.models.CoinTag
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CoinTagDTO(
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("name")
    val name: String?
)

fun CoinTagDTO.toCoinTag(): CoinTag {
    return CoinTag(
        id = id.orEmpty(),
        name = name.orEmpty()
    )
}