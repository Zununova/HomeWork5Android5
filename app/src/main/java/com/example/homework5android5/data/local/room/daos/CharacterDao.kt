package com.example.homework5android5.data.local.room.daos

import androidx.room.*
import com.example.homework5android5.data.local.room.entites.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_entity")
    suspend fun fetchLocalCharacter(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalCharacter(character: List<CharacterEntity>)

    @Update
    suspend fun updateLocalCharacter(characterEntity: CharacterEntity)

    @Delete
    suspend fun deleteLocalCharacter(characterEntity: CharacterEntity)

    @Query("DELETE FROM character_entity")
    suspend fun clearCharacter()

    @Query("SELECT * FROM character_entity WHERE name LIKE :searchQuery")
    fun searchCharacters(searchQuery: String): Flow<List<CharacterEntity>>

}
