package com.example.homework5android5.data.remote.apiservices

import com.example.homework5android5.data.remote.dtos.CharacterDto
import com.example.homework5android5.data.remote.dtos.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApiService {

    @GET("character")
    suspend fun fetchCharacter(): CharacterResponse<CharacterDto>

    @GET("character/{id}")
    suspend fun fetchCharacterById(
        @Path("id") id: Int
    ): CharacterDto
}