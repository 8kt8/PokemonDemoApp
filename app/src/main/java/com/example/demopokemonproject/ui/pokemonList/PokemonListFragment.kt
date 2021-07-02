package com.example.demopokemonproject.ui.pokemonList

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.demopokemonproject.R
import com.example.demopokemonproject.databinding.PokemonListFragmentBinding
import com.example.demopokemonproject.ui.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment(R.layout.pokemon_list_fragment) {

    private val viewModel: PokemonViewModel by viewModels()

    private val binding: PokemonListFragmentBinding by viewBinding(PokemonListFragmentBinding::bind)

    private val pokemonComponentRecyclerView = PokemonComponentRecyclerView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        pokemonComponentRecyclerView.init(binding.recyclerViewPokemon)
        pokemonComponentRecyclerView.onItemClickListener = {
            val direction = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonItemFragment(it.id)
            findNavController().navigate(direction)
        }
    }

    private fun observeViewModel() {
        viewModel.refresh()
        viewModel.pokemonList.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false
            pokemonComponentRecyclerView.update(it)
        }
    }
}