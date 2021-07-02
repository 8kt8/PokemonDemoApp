package com.example.demopokemonproject.core.repository

import com.example.demopokemonproject.core.model.Pokemon
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val getAllPokemonUseCase: GetAllPokemonUseCase
) {

    fun get(id: Int): Flowable<Pokemon> = getAllPokemonUseCase.get()
        .map { it.find { pokemon ->
            pokemon.id == id }
        }
}