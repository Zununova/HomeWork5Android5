package com.example.homework5android5.di

import android.content.Context
import androidx.room.Room
import com.example.homework5android5.data.local.room.RickAndMortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRickAndMortyDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RickAndMortyDatabase::class.java, "rick_and_morty")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideCharacterDao(dataBase: RickAndMortyDatabase) = dataBase.characterDao()
}