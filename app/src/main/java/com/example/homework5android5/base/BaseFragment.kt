package com.example.homework5android5.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.homework5android5.presentation.state.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(@LayoutRes id: Int) : Fragment(id) {

    abstract val binding: VB
    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupListeners()
        setupSubscribes()
    }

    protected open fun initialize() {}

    protected open fun setupSubscribes() {}

    protected open fun setupListeners() {}

    protected open fun <T> fetchDataRickAndMorty(
        stateDataRickANdMorty: () -> StateFlow<UiState<List<T>>>,
        listAdapter: ListAdapter<T, *>
    ) {
        lifecycleScope.launch {
            stateDataRickANdMorty().collect {
                when (it) {
                    is UiState.Error -> Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    is UiState.Loading -> Toast.makeText(
                        requireContext(),
                        "Loading...",
                        Toast.LENGTH_SHORT
                    ).show()
                    is UiState.Success -> {
                        listAdapter.submitList(it.data)
                    }
                }
            }
        }
    }
}