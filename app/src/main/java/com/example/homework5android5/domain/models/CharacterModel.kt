package com.example.homework5android5.domain.models

data class CharacterModel(
    val created: String,
//    val episode: List<String>,
    val gender: String,
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