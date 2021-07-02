package com.example.demopokemonproject.db

import com.example.demopokemonproject.core.model.Pokemon
import com.example.demopokemonproject.core.service.PokemonResponse
import com.example.demopokemonproject.core.service.PokemonsResponse
import javax.inject.Inject

class PokemonDBMapper @Inject constructor() {

    fun map(listOfPokemonDB: List<PokemonDB>): List<Pokemon> =
        listOfPokemonDB.map {
            Pokemon(
                id = it.id,
                name = it.name,
                url = it.url
            )
        }
}