package com.example.demopokemonproject.core.di

import android.content.Context
import com.example.demopokemonproject.core.config.BackendConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HttpModule {

//    @Provides
//    @Singleton
//    internal fun provideOkhttpCache(
//        @ApplicationContext context: Context
//    ): Cache {
//        val cacheSize = 10 * 1024 * 1024 // 10 MB
//        return Cache(context.cacheDir, cacheSize.toLong())
//    }

    @Provides
    @Singleton
    internal fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        config: BackendConfig
    ): OkHttpClient = OkHttpClient().newBuilder()
        .readTimeout(config.readTimeout, TimeUnit.SECONDS)
        .connectTimeout(config.connectionTimeout, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}