package com.example.homework5android5.data.local.room.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.homework5android5.domain.models.CharacterModel

@Entity(tableName = "character_entity")
data class CharacterEntity(
    val created: String,
//    val episode: List<String>,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    //    val locationDto: LocationDto,
    val name: String,
    //    val originDto: OriginDto,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun CharacterEntity.toDomain() = CharacterModel(
    created, gender, id, image, name, species, status, type, url
)

fun CharacterModel.toEntity() = CharacterEntity(
    created, gender, id, image, name, species, status, type, url
)