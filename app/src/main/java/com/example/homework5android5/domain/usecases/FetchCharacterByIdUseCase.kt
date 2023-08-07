package com.example.homework5android5.domain.usecases

import com.example.homework5android5.domain.repositores.CharacterRepository
import javax.inject.Inject

class FetchCharacterByIdUseCase @Inject constructor(private val repository: CharacterRepository) {

    operator fun invoke(id: Int) = repository.fetchCharacterById(id)
}