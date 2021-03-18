package com.example.musicsearchapp.api


import com.google.gson.annotations.SerializedName

data class OpensearchQuery(
    @SerializedName("role")
    val role: String,
    @SerializedName("startPage")
    val startPage: String,
    @SerializedName("#text")
    val text: String
)