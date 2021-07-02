package com.example.demopokemonproject.core.repository

import javax.inject.Inject

class GetAllPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    fun get() = pokemonRepository.getAll()
}