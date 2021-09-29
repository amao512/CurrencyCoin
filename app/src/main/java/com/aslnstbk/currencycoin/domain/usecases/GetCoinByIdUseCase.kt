package com.aslnstbk.currencycoin.domain.usecases

import com.aslnstbk.currencycoin.data.models.toCoinDetail
import com.aslnstbk.currencycoin.domain.models.CoinDetail
import com.aslnstbk.currencycoin.domain.repositories.CurrencyRepository
import com.aslnstbk.currencycoin.presentation.models.ResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinByIdUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    operator fun invoke(coinId: String): Flow<ResponseData<CoinDetail, String>> = flow {
        try {
            val coin = repository.getCoinById(coinId = coinId).toCoinDetail()

            emit(ResponseData.Success(coin))
        } catch (e: Exception) {
            emit(ResponseData.Error(error = e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}