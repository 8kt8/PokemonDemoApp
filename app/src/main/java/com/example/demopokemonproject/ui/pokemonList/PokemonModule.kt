package com.example.demopokemonproject.ui.pokemonList

import com.example.demopokemonproject.core.repository.GetAllPokemonUseCase
import com.example.demopokemonproject.core.repository.PokemonRepository
import com.example.demopokemonproject.core.repository.RefreshPokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class PokemonModule {

    @Provides
    fun provideRefreshUserCase(pokemonRepository: PokemonRepository) =
        RefreshPokemonUseCase(pokemonRepository)

    @Provides
    fun provideGetUserCase(pokemonRepository: PokemonRepository) =
        GetAllPokemonUseCase(pokemonRepository)

}