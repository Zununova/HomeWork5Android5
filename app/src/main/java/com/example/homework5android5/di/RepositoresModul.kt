package com.example.homework5android5.di

import com.example.homework5android5.data.repositores.CharacterRepositoryImpl
import com.example.homework5android5.domain.repositores.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoresModul {

    @Binds
    fun bindsCharacterRepositores(characterRepositoryImpl: CharacterRepositoryImpl) : CharacterRepository
}