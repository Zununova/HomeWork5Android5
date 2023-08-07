package com.example.homework5android5.data.repositores

import com.example.homework5android5.data.local.room.daos.CharacterDao
import com.example.homework5android5.data.local.room.entites.toDomain
import com.example.homework5android5.data.local.room.entites.toEntity
import com.example.homework5android5.data.remote.apiservices.CharacterApiService
import com.example.homework5android5.data.remote.dtos.toDomain
import com.example.homework5android5.domain.Either
import com.example.homework5android5.domain.models.CharacterModel
import com.example.homework5android5.domain.repositores.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterApiService,
    private val characterDao: CharacterDao
) :
    CharacterRepository {

    override fun fetchCharacter() = flow<Either<String, List<CharacterModel>>> {
        emit(Either.Right(service.fetchCharacter().results.map { it.toDomain() }))
    }.flowOn(Dispatchers.IO).catch {
        emit(Either.Left(it.localizedMessage ?: "You have big problems"))
    }

    override fun fetchCharacterById(id: Int) = flow<Either<String, CharacterModel>> {
        emit(Either.Right(service.fetchCharacterById(id).toDomain()))
    }.flowOn(Dispatchers.IO).catch {
        emit(Either.Left(it.localizedMessage ?: "You have big problems"))
    }

    override suspend fun insertLocalCharacter(character: List<CharacterModel>) {
        withContext(Dispatchers.IO) {
            characterDao.insertLocalCharacter(character.map { it.toEntity() })
        }
    }

    override suspend fun deleteLocalCharacter(character: CharacterModel) {
        withContext(Dispatchers.IO) {
            characterDao.deleteLocalCharacter(character.toEntity())
        }
    }

    override suspend fun fetchLocalCharacter() = flow<Either<String, List<CharacterModel>>> {
        emit(Either.Right(characterDao.fetchLocalCharacter().map { it.toDomain() }))
    }.flowOn(Dispatchers.IO).catch {
        emit(Either.Left(it.localizedMessage ?: "Error"))
    }

    override suspend fun updateLocalCharacter(character: CharacterModel) {
        withContext(Dispatchers.IO) {
            characterDao.updateLocalCharacter(character.toEntity())
        }
    }

    override suspend fun clear() {
        withContext(Dispatchers.IO) {
            characterDao.clearCharacter()
        }
    }

    override fun searchLocalCharacter(characterName: String) =
        characterDao.searchCharacters(characterName)
            .map { it.map { characterModel -> characterModel.toDomain() } }
}