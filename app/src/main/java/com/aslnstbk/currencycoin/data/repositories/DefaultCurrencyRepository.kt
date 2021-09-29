package com.aslnstbk.currencycoin.data.repositories

import com.aslnstbk.currencycoin.data.api.CurrencyApi
import com.aslnstbk.currencycoin.data.models.CoinDTO
import com.aslnstbk.currencycoin.data.models.CoinDetailDTO
import com.aslnstbk.currencycoin.domain.repositories.CurrencyRepository
import javax.inject.Inject

class DefaultCurrencyRepository @Inject constructor(
    private val api: CurrencyApi
) : CurrencyRepository {

    override suspend fun getCoins(): List<CoinDTO> = api.getCoins()

    override suspend fun getCoinById(coinId: String): CoinDetailDTO {
        return api.getCoinById(coinId = coinId)
    }
}