package com.aslnstbk.currencycoin.domain.usecases

import com.aslnstbk.currencycoin.data.models.FavoriteCoinEntity
import com.aslnstbk.currencycoin.domain.repositories.CurrencyRepository
import javax.inject.Inject

class GetFavoriteCoinByIdUseCase @Inject constructor(
    private val repository: CurrencyRepository
){

    suspend operator fun invoke(coinId: String): FavoriteCoinEntity? {
        return repository.getFavoriteCoinById(coinId)
    }
}