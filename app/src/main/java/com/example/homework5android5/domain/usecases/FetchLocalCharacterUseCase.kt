package com.example.homework5android5.domain.usecases

import com.example.homework5android5.domain.repositores.CharacterRepository
import javax.inject.Inject

class FetchLocalCharacterUseCase @Inject constructor(private val repository: CharacterRepository) {

    operator suspend fun invoke() = repository.fetchLocalCharacter()
}