package com.example.homework5android5.presentation.ui.fragments.home.character

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.homework5android5.base.BaseViewModel
import com.example.homework5android5.domain.Either
import com.example.homework5android5.domain.models.CharacterModel
import com.example.homework5android5.domain.usecases.*
import com.example.homework5android5.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val fetchCharacterUseCase: FetchCharacterUseCase,
    private val insertLocalCharacterUseCase: InsertLocalCharacterUseCase,
    private val fetchLocalCharacterUseCase: FetchLocalCharacterUseCase,
    private val deleteLocalCharacterUseCase: DeleteLocalCharacterUseCase,
    private val updateLocalCharacterUseCase: UpdateLocalCharacterUseCase,
    private val clearLocalDataUseCase: ClearLocalDataUseCase,
    private val searchLocalCharacterUseCase: SearchLocalCharacterUseCase
) :
    BaseViewModel() {

    private var _characterState = MutableStateFlow<UiState<List<CharacterModel>>>(UiState.Loading())
    val characterState = _characterState.asStateFlow()

    private var _localState = MutableStateFlow<UiState<List<CharacterModel>>>(UiState.Loading())
    val localState = _localState.asStateFlow()

    init {
        fetchCharacter()
        insertLocalCharacter()
        fetchLocalCharacter()
    }

    private fun fetchCharacter() = fetchData(
        fetchData = { fetchCharacterUseCase() },
        dataState = _characterState
    )

    private fun insertLocalCharacter() {
        viewModelScope.launch {
            characterState.collect {
                when (it) {
                    is UiState.Error -> Log.e(TAG, it.message)
                    is UiState.Loading -> Log.e(TAG, "loading")
                    is UiState.Success -> insertLocalCharacterUseCase(it.data)
                }
            }
        }
    }

    fun deletedLocalCharacter(characterModel: CharacterModel) {
        viewModelScope.launch {
            deleteLocalCharacterUseCase(characterModel)
        }
    }

    private fun fetchLocalCharacter() {
        viewModelScope.launch {
            fetchLocalCharacterUseCase().collect {
                when (it) {
                    is Either.Left -> _localState.value = UiState.Error(it.message)
                    is Either.Right -> _localState.value = UiState.Success(it.data)
                }
            }
        }
    }

    fun updateLocalCharacter(characterModel: CharacterModel) {
        viewModelScope.launch {
            updateLocalCharacterUseCase(characterModel)
        }
    }

    fun clearLocalData() {
        viewModelScope.launch {
            clearLocalDataUseCase()
        }
    }

    fun searchLocalCharacter(characterName: String) = searchLocalCharacterUseCase(characterName)
}