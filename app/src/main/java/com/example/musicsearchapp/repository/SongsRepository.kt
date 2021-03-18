package com.example.musicsearchapp.repository

import com.example.musicsearchapp.api.MusicApiService
import com.example.musicsearchapp.api.Track

class SongsRepository {

    suspend fun getSongs(track: String?): List<Track> {
        val apiCall = MusicApiService()
        var trackList: List<Track> = ArrayList<Track>()
            val tracks = apiCall.getSongs(track)
            trackList = tracks.results.trackMatches.track

        return trackList

    }

}