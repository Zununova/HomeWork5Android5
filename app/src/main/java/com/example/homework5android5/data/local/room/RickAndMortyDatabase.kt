package com.example.homework5android5.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework5android5.data.local.room.daos.CharacterDao
import com.example.homework5android5.data.local.room.entites.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 3, exportSchema = false)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}