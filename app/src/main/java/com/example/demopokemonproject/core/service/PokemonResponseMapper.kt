package com.example.demopokemonproject.core.service

import com.example.demopokemonproject.core.config.BackendConfig
import com.example.demopokemonproject.db.PokemonDB
import javax.inject.Inject

class PokemonResponseMapper @Inject constructor(
    private val backendConfig: BackendConfig
) {

    fun map(response: PokemonsResponse): List<PokemonDB> =
        response.results.mapIndexed { index, pokemonResponse ->
            PokemonDB(
                id = index,
                name = pokemonResponse.name,
                url = getUrl(index)
            )
        }

    private fun getUrl(index: Int) = "${backendConfig.imageUrl}${index + 1}.png"
}