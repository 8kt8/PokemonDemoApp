package com.example.demopokemonproject.core.repository

import com.example.demopokemonproject.core.CoreSchedulers
import com.example.demopokemonproject.db.PokemonDBMapper
import com.example.demopokemonproject.core.CoreSchedulersImpl
import com.example.demopokemonproject.core.model.Pokemon
import com.example.demopokemonproject.core.service.PokemonResponseMapper
import com.example.demopokemonproject.core.service.PokemonService
import com.example.demopokemonproject.db.PokemonDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao,
    private val pokemonResponseMapper: PokemonResponseMapper,
    private val pokemonDBMapper: PokemonDBMapper,
    private val coreSchedulers: CoreSchedulers
) {

    fun refreshAll(): Completable =
        pokemonService.getAll()
            .subscribeOn(coreSchedulers.networkIO)
            .map(pokemonResponseMapper::map)
            .flatMapCompletable(pokemonDao::insertAll)
            .subscribeOn(coreSchedulers.dbIO)

    fun getAll(): Flowable<List<Pokemon>> =
        pokemonDao.getAll()
            .map(pokemonDBMapper::map)
            .subscribeOn(coreSchedulers.dbIO)

}