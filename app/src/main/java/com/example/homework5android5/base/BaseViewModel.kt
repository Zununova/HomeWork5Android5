package com.example.homework5android5.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework5android5.domain.Either
import com.example.homework5android5.presentation.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected open fun<T> fetchData(
        fetchData: () -> Flow<Either<String, T>>,
        dataState: MutableStateFlow<UiState<T>>
    ) {
        viewModelScope.launch {
            fetchData.invoke().collect {
                when (it) {
                    is Either.Left -> it.message.let { message ->
                        dataState.value = UiState.Error(message)
                    }
                    is Either.Right -> it.data.let { data ->
                        dataState.value = UiState.Success(data)
                    }
                }
            }
        }
    }
}