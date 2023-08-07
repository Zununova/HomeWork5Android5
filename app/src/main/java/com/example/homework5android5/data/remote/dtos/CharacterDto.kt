package com.example.homework5android5.data.remote.dtos


import com.example.homework5android5.domain.models.CharacterModel
import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("created")
    val created: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("locationDto")
    val locationDto: LocationDto,
    @SerializedName("name")
    val name: String,
    @SerializedName("originDto")
    val originDto: OriginDto,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)

fun CharacterDto.toDomain() = CharacterModel(
    created,
    gender,
    id,
    image,
    name,
    species,
    status,
    type,
    url
)