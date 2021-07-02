package com.example.demopokemonproject.db.di

import android.content.Context
import androidx.room.Room
import com.example.demopokemonproject.db.AppDatabase
import com.example.demopokemonproject.db.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providePokemonDB(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePokeDao(appDatabase: AppDatabase): PokemonDao = appDatabase.pokemonDao()
}