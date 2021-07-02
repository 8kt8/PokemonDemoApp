package com.example.demopokemonproject.ui.pokemonItem

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.demopokemonproject.R
import com.example.demopokemonproject.databinding.FragmentItemBinding
import com.example.demopokemonproject.ui.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonItemFragment: Fragment(R.layout.fragment_item) {

    private val viewModel: PokemonItemViewModel by viewModels()

    private val binding: FragmentItemBinding by viewBinding(FragmentItemBinding::bind)

    private val args: PokemonItemFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getPokemon(args.id).observe(viewLifecycleOwner){
            with(binding){
                name.text = it.name
                Glide.with(requireContext()).load(it.url)
                    .into(image)
            }
        }
    }
}