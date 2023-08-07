package com.example.homework5android5.presentation.ui.fragments.home.detail

import com.example.homework5android5.base.BaseViewModel
import com.example.homework5android5.domain.models.CharacterModel
import com.example.homework5android5.domain.usecases.FetchCharacterByIdUseCase
import com.example.homework5android5.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(private val fetchCharacterByIdUseCase: FetchCharacterByIdUseCase) :
    BaseViewModel() {

    private var _stateCharacter = MutableStateFlow<UiState<CharacterModel>>(UiState.Loading())
    val stateCharacter = _stateCharacter.asStateFlow()

    fun fetchCharacterById(id: Int) = fetchData(
        fetchData = { fetchCharacterByIdUseCase(id) },
        dataState = _stateCharacter
    )

}