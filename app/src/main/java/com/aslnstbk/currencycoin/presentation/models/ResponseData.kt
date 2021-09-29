package com.aslnstbk.currencycoin.presentation.models

sealed class ResponseData<out Result, out Error> {
    data class Success<Result>(val data: Result) : ResponseData<Result, Nothing>()
    data class Error<Error>(val error: Error) : ResponseData<Nothing, Error>()
}
