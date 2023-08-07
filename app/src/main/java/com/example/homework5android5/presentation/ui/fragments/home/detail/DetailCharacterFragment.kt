package com.example.homework5android5.presentation.ui.fragments.home.detail

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.homework5android5.R
import com.example.homework5android5.base.BaseFragment
import com.example.homework5android5.databinding.FragmentDetailCharacterBinding
import com.example.homework5android5.presentation.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCharacterFragment :
    BaseFragment<FragmentDetailCharacterBinding, DetailCharacterViewModel>(R.layout.fragment_detail_character) {
    override val binding by viewBinding(FragmentDetailCharacterBinding::bind)
    override val viewModel by viewModels<DetailCharacterViewModel>()
    private val args by navArgs<DetailCharacterFragmentArgs>()


    override fun initialize() {
        viewModel.fetchCharacterById(args.id)
    }

    override fun setupSubscribes() {
        lifecycleScope.launch {
            viewModel.stateCharacter.collect {
                when(it){
                    is UiState.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    is UiState.Loading -> Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                    is UiState.Success -> {
                        Glide.with(binding.detailCharacterImage).load(it.data.image).into(binding.detailCharacterImage)
                        binding.tvName.text = it.data.name
                    }
                }
            }
        }
    }

}