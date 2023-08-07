package com.example.homework5android5.domain.usecases

import androidx.room.Insert
import com.example.homework5android5.domain.models.CharacterModel
import com.example.homework5android5.domain.repositores.CharacterRepository
import javax.inject.Inject

class InsertLocalCharacterUseCase @Inject constructor( private val repository: CharacterRepository) {

    operator suspend fun invoke(character: List<CharacterModel>) = repository.insertLocalCharacter(character)
}