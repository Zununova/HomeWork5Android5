package com.example.homework5android5.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class CharacterResponse<T>(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<T>
)