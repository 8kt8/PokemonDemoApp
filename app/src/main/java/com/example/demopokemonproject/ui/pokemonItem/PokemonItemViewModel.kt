package com.example.demopokemonproject.ui.pokemonItem

import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import com.example.demopokemonproject.core.repository.GetAllPokemonUseCase
import com.example.demopokemonproject.core.repository.GetPokemonUseCase
import com.example.demopokemonproject.core.repository.RefreshPokemonUseCase
import com.example.demopokemonproject.ui.common.BaseViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonItemViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
) : BaseViewModel() {

    fun getPokemon(id: Int) = fromPublisher(getPokemonUseCase.get(id))

}