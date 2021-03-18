package com.example.musicsearchapp.ui

import androidx.lifecycle.ViewModel
import com.example.musicsearchapp.api.Track
import com.example.musicsearchapp.repository.SongsRepository

class SongsViewModel :ViewModel(){

    private var songsRepository = SongsRepository()
    private var songs: List<Track> = ArrayList()

    suspend fun getSongs(track: String?): List<Track> {
        songs = songsRepository.getSongs(track)
        return songs.sortedBy { it.listeners }
    }
}