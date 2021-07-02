package com.example.demopokemonproject.core.config

import javax.inject.Inject

class BackendConfig @Inject constructor() {
    val apiUrl = "https://pokeapi.co/api/v2/"
    val imageUrl = "https://pokeres.bastionbot.org/images/pokemon/"
    val readTimeout = 60L
    val connectionTimeout = 60L
}