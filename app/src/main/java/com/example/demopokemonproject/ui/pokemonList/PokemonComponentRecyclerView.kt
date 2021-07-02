package com.example.demopokemonproject.ui.pokemonList

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demopokemonproject.core.model.Pokemon
import com.example.demopokemonproject.core.service.PokemonResponse

class PokemonComponentRecyclerView {

    private lateinit var pokemonAdapter: PokemonAdapter

    var onItemClickListener: (Pokemon) -> Unit = {}
        set(value) {
            field = value
            pokemonAdapter.onItemClickListener = value
        }

    fun init(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        pokemonAdapter = PokemonAdapter(recyclerView.context, emptyList())
        recyclerView.adapter = pokemonAdapter
    }

    fun update(updatedList: List<Pokemon>) = pokemonAdapter.updateList(updatedList)
}