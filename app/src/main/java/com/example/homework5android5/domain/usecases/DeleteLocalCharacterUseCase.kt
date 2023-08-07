package com.example.homework5android5.domain.usecases

import com.example.homework5android5.domain.models.CharacterModel
import com.example.homework5android5.domain.repositores.CharacterRepository
import javax.inject.Inject

class DeleteLocalCharacterUseCase @Inject constructor(private val repository: CharacterRepository) {

    suspend operator fun invoke(characterModel: CharacterModel) = repository.deleteLocalCharacter(characterModel)
}