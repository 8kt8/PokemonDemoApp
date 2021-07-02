package com.example.demopokemonproject.ui.pokemonList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demopokemonproject.core.model.Pokemon
import com.example.demopokemonproject.databinding.ListItemBinding

internal class PokemonAdapter(val context: Context, var list: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private lateinit var binding: ListItemBinding

    var onItemClickListener: (Pokemon) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(context)
        binding = ListItemBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getPokemonAt(position)
        holder.binding.name.text = pokemon.name
        holder.binding.root.setOnClickListener { onItemClickListener(pokemon) }
        val url = pokemon.url
        Glide.with(context).load(url)
            .into(holder.binding.image)
    }

    override fun getItemCount(): Int = list.size

    internal inner class PokemonViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateList(updatedList: List<Pokemon>) {
        list = updatedList
        notifyDataSetChanged()
    }

    fun getPokemonAt(position: Int): Pokemon = list[position]

}