package com.example.musicsearchapp.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicsearchapp.R

class SongsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val songName: TextView = itemView.findViewById(R.id.songName)
    val totalListener: TextView = itemView.findViewById(R.id.totalListeners)
}