package com.example.demopokemonproject.core.service

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)