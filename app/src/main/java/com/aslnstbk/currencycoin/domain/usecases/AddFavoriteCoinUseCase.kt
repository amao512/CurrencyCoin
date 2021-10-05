package com.aslnstbk.currencycoin.domain.usecases

import com.aslnstbk.currencycoin.data.models.toFavoriteCoinEntity
import com.aslnstbk.currencycoin.domain.models.CoinDetail
import com.aslnstbk.currencycoin.domain.repositories.CurrencyRepository
import javax.inject.Inject

class AddFavoriteCoinUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    suspend operator fun invoke(coin: CoinDetail) {
        repository.addFavoriteIcon(coin.toFavoriteCoinEntity())
    }
}