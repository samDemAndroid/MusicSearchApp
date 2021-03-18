package com.example.musicsearchapp.api


import com.google.gson.annotations.SerializedName

data class TrackMatches(
    @SerializedName("track")
    val track: List<Track>
)