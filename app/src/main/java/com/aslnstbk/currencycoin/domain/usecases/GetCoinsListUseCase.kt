package com.aslnstbk.currencycoin.domain.usecases

import com.aslnstbk.currencycoin.data.models.toCoin
import com.aslnstbk.currencycoin.domain.models.Coin
import com.aslnstbk.currencycoin.domain.repositories.CurrencyRepository
import com.aslnstbk.currencycoin.presentation.models.ResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetCoinsListUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    operator fun invoke(): Flow<ResponseData<List<Coin>, String>> = flow {
        try {
            val coinsList = repository.getCoins().map { it.toCoin() }

            emit(ResponseData.Success(coinsList))
        } catch (e: Exception) {
            emit(ResponseData.Error(error = e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}