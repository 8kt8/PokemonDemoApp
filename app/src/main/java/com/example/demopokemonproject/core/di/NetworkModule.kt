package com.example.demopokemonproject.core.di

import com.example.demopokemonproject.core.config.BackendConfig
import com.example.demopokemonproject.core.service.PokemonService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    internal fun provideRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory =
        RxJava3CallAdapterFactory.create()

    @Provides
    @Singleton
    internal fun provideConfig(): BackendConfig = BackendConfig()

    @Provides
    @Singleton
    internal fun provideRetrofitBuilder(
        backendConfig: BackendConfig,
        httpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
        rxJavaCallAdapterFactory: RxJava3CallAdapterFactory
    ): PokemonService =
        Retrofit.Builder()
            .baseUrl(backendConfig.apiUrl)
            .client(httpClient)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .build()
            .create(PokemonService::class.java)
}