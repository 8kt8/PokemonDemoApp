package com.example.demopokemonproject.ui.pokemonList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.demopokemonproject.core.model.Pokemon
import com.example.demopokemonproject.core.repository.GetAllPokemonUseCase
import com.example.demopokemonproject.core.repository.RefreshPokemonUseCase
import com.example.demopokemonproject.testCompletableFor
import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Flowable
import org.junit.Rule
import org.junit.Test

class PokemonViewModelTest {

    private val refreshPokemonUseCase: RefreshPokemonUseCase = mockk()
    private val getPokemonUseCase: GetAllPokemonUseCase = mockk()

    private val sut by lazy { PokemonViewModel(refreshPokemonUseCase, getPokemonUseCase) }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun refresh() {
        every { getPokemonUseCase.get() } returns Flowable.just(emptyList())
        val observer = testCompletableFor { refreshPokemonUseCase.refresh() }

        sut.refresh()
        assert(observer.hasSubscription())
    }

    @Test
    fun getPokemonList() {
        val pokemon: Pokemon = mockk()
        every { getPokemonUseCase.get() } returns Flowable.just(listOf(pokemon))

        sut.pokemonList.test().assertValue(listOf(pokemon))
    }
}