package com.aslnstbk.currencycoin.domain.usecases

import com.aslnstbk.currencycoin.domain.repositories.CurrencyRepository
import javax.inject.Inject

class RemoveFavoriteCoinUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    suspend operator fun invoke(coinId: String) {
        repository.removeFavoriteIcon(coinId)
    }
}