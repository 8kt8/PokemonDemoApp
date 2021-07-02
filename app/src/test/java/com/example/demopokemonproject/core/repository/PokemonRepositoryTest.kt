package com.example.demopokemonproject.core.repository

import com.example.demopokemonproject.core.CoreSchedulers
import com.example.demopokemonproject.core.TestSchedulers
import com.example.demopokemonproject.core.model.Pokemon
import com.example.demopokemonproject.core.service.PokemonResponseMapper
import com.example.demopokemonproject.core.service.PokemonService
import com.example.demopokemonproject.core.service.PokemonsResponse
import com.example.demopokemonproject.db.PokemonDB
import com.example.demopokemonproject.db.PokemonDBMapper
import com.example.demopokemonproject.db.PokemonDao
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import org.junit.Test
import java.lang.RuntimeException

class PokemonRepositoryTest {

    private val pokemonService: PokemonService = mockk()
    private val pokemonDao: PokemonDao = mockk()
    private val pokemonResponseMapper: PokemonResponseMapper = mockk()
    private val pokemonDBMapper: PokemonDBMapper = mockk()
    private val coreSchedulers: CoreSchedulers = TestSchedulers()

    private val sut = PokemonRepository(pokemonService, pokemonDao, pokemonResponseMapper, pokemonDBMapper, coreSchedulers)

    private val pokemonDb: PokemonDB = mockk()
    private val pokemon: Pokemon = mockk()


    @Test
    fun refreshAll() {
        val response: PokemonsResponse = mockk()
        every { pokemonService.getAll() } returns Flowable.just(response)
        every { pokemonResponseMapper.map(response) } returns listOf(pokemonDb)
        every { pokemonDao.insertAll(listOf(pokemonDb)) } returns Completable.complete()

        sut.refreshAll().test()
            .assertComplete()
    }

    @Test
    fun `refreshAll when error`() {
        val error = RuntimeException()
        every { pokemonService.getAll() } returns Flowable.error(error)

        sut.refreshAll().test()
            .assertError(error)
    }

    @Test
    fun getAll() {
        every { pokemonDao.getAll() } returns Flowable.just(listOf(pokemonDb))
        every { pokemonDBMapper.map(listOf(pokemonDb)) } returns listOf(pokemon)

        sut.getAll().test().assertValue(listOf(pokemon))
    }

    @Test
    fun `getAll when error`() {
        val error = RuntimeException()
        every { pokemonDao.getAll() } returns Flowable.error(error)

        sut.getAll().test().assertError(error)
    }
}