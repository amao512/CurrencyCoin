package com.aslnstbk.currencycoin.di

import com.aslnstbk.currencycoin.data.api.CurrencyApi
import com.aslnstbk.currencycoin.data.repositories.DefaultCurrencyRepository
import com.aslnstbk.currencycoin.domain.repositories.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesCurrencyApi(retrofit: Retrofit): CurrencyApi {
        return retrofit.create(CurrencyApi::class.java)
    }

    @Provides
    fun providesCurrencyRepository(api: CurrencyApi): CurrencyRepository {
        return DefaultCurrencyRepository(api)
    }
}