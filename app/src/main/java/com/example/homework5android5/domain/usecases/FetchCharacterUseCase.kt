package com.example.homework5android5.domain.usecases

import com.example.homework5android5.domain.repositores.CharacterRepository
import javax.inject.Inject

class FetchCharacterUseCase @Inject constructor(private val repository: CharacterRepository) {

    operator fun invoke () = repository.fetchCharacter()
}