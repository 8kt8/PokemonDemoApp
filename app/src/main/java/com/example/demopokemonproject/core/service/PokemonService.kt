package com.example.demopokemonproject.core.service

import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon")
    fun getAll(): Flowable<PokemonsResponse>
}