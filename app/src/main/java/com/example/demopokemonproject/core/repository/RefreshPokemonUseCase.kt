package com.example.demopokemonproject.core.repository

import javax.inject.Inject

class RefreshPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    fun refresh() = pokemonRepository.refreshAll()
}