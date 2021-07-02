package com.example.demopokemonproject.ui.pokemonList

import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import com.example.demopokemonproject.core.repository.GetAllPokemonUseCase
import com.example.demopokemonproject.core.repository.RefreshPokemonUseCase
import com.example.demopokemonproject.ui.common.BaseViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val refreshPokemonUseCase: RefreshPokemonUseCase,
    getPokemonUseCase: GetAllPokemonUseCase
) : BaseViewModel() {

    fun refresh() {
        refreshPokemonUseCase.refresh()
            .subscribe({}, { Logger.e("refresh Pokemon error ${it.localizedMessage}") })
            .remember()
    }

    val pokemonList = fromPublisher(getPokemonUseCase.get())

}