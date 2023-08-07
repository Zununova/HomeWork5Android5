package com.example.homework5android5.presentation.ui.fragments.home.character

import android.content.Context
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5android5.R
import com.example.homework5android5.base.BaseFragment
import com.example.homework5android5.databinding.FragmentCharacterBinding
import com.example.homework5android5.domain.models.CharacterModel
import com.example.homework5android5.presentation.ui.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<FragmentCharacterBinding, CharacterViewModel>(R.layout.fragment_character) {
    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel by viewModels<CharacterViewModel>()
    private val characterAdapter = CharacterAdapter(this::onCLick, this::deleteItem)

    private fun deleteItem(character: CharacterModel) {
        viewModel.deletedLocalCharacter(character)
        Toast.makeText(requireContext(), "item was deleted", Toast.LENGTH_SHORT).show()
    }


    private fun onCLick(character: CharacterModel) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToDetailCharacterFragment(
                character.id
            )
        )
    }

    override fun initialize() {
        binding.recyclerViewCharacterFragment.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun setupSubscribes() {
        if (isConnectedToInternet()) {
            fetchCharacter()

        } else {
            fetchLocalCharacter()
        }
    }

    override fun setupListeners() {
        searchLocalCharacters()
        addPerson()
        updateLocalCharacter()
        clearLocalData()
    }

    private fun searchLocalCharacters() = with(binding) {
        svSearchCharacters.setOnQueryTextListener((object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    viewModel.searchLocalCharacter(query ?: "").collect {
                        Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
                        characterAdapter.submitList(it)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch {
                    viewModel.searchLocalCharacter(newText ?: "").collect {
                        characterAdapter.submitList(it)
                    }
                }
                return true
            }

        }))
    }

    private fun addPerson() = with(binding) {
        btnAddLocalPerson.setOnClickListener {
            llRecyclerView.isGone = true
            llUpdateElement.isVisible = true
        }
    }

    private fun updateLocalCharacter() = with(binding) {
        btnUpdateLocalCharacter.setOnClickListener {
            val name = etInputName.text.toString().trim()
            val url = etInputUrl.text.toString().trim()
            llRecyclerView.isVisible = true
            llUpdateElement.isGone = true
            viewModel.updateLocalCharacter(CharacterModel("", "", 1, url, name, "", "", "", ""))
        }
    }

    private fun clearLocalData() {
        binding.btnClear.setOnClickListener {
            viewModel.clearLocalData()
        }
    }

    private fun fetchCharacter() = fetchDataRickAndMorty(
        { viewModel.characterState },
        characterAdapter
    )


    private fun fetchLocalCharacter() = fetchDataRickAndMorty(
        { viewModel.localState },
        characterAdapter
    )

    // This function checked the internet connection
    private fun isConnectedToInternet(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {
            actNw.hasTransport(android.net.NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(android.net.NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(android.net.NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}