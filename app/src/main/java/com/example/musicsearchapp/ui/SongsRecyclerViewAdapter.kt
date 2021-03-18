package com.example.musicsearchapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicsearchapp.R
import com.example.musicsearchapp.api.Track

class SongsRecyclerViewAdapter(private val dataSet: List<Track>?, val contex: Context) : RecyclerView.Adapter<SongsViewHolder>() {


    //private var dataSet: List<Track> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return SongsViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (dataSet != null) {
            return dataSet.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.songName.text = dataSet?.get(position)?.name
        holder.totalListener.text = dataSet?.get(position)?.listeners
    }
}