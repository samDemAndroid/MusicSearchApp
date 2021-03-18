package com.example.musicsearchapp.api


import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("results")
    val results: Results
)