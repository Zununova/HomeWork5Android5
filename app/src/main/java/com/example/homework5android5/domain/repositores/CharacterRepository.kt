package com.example.homework5android5.domain.repositores

import com.example.homework5android5.data.local.room.entites.CharacterEntity
import com.example.homework5android5.domain.Either
import com.example.homework5android5.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun fetchCharacter(): Flow<Either<String, List<CharacterModel>>>

    fun fetchCharacterById(id: Int): Flow<Either<String, CharacterModel>>

    suspend fun insertLocalCharacter(character: List<CharacterModel>)

    suspend fun deleteLocalCharacter(character: CharacterModel)

    suspend fun fetchLocalCharacter(): Flow<Either<String, List<CharacterModel>>>

    suspend fun updateLocalCharacter(character: CharacterModel)

    suspend fun clear()

    fun searchLocalCharacter(characterName: String) : Flow<List<CharacterModel>>
}