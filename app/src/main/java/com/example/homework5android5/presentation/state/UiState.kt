package com.example.homework5android5.presentation.state

sealed class UiState<T> {

    class Loading<T> : UiState<T>()
    class Error<T>(val message: String) : UiState<T>()
    class Success<T>(val data: T) : UiState<T>()
}