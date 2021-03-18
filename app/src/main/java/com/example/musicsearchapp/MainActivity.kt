package com.example.musicsearchapp

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicsearchapp.api.Track
import com.example.musicsearchapp.ui.SongsRecyclerViewAdapter
import com.example.musicsearchapp.ui.SongsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    var order = "descending"
    private lateinit var trackList: List<Track>
    private lateinit var searchView: SearchView
    private val viewModel: SongsViewModel by lazy {
        ViewModelProvider(this)[SongsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView = findViewById(R.id.searchView)
        doSearch()
    }

    private fun setUpRecyclerView(trackList: List<Track>) {
        val recyclerView: RecyclerView = findViewById(R.id.songsRecyclerView)
        val layoutManager = LinearLayoutManager(this)
        val recyclerViewAdapter = SongsRecyclerViewAdapter(trackList, this)
        recyclerView.layoutManager = layoutManager
        recyclerViewAdapter.notifyDataSetChanged()
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun doSearch() {
        val inputManger = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        searchView.imeOptions = searchView.imeOptions or EditorInfo.IME_FLAG_NO_EXTRACT_UI
        searchView.queryHint = "Search Here"
        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val progressbar = ProgressBar(this@MainActivity)
                //change this scope
                GlobalScope.launch(Dispatchers.Main) {
                    trackList = viewModel.getSongs(query.orEmpty())
                    inputManger.hideSoftInputFromWindow(searchView.applicationWindowToken, 0)
                    if (order.equals("descending")) {
                        setUpRecyclerView(trackList.sortedByDescending { it.listeners.toInt() })
                    }
                    if (order.equals("ascending")) {
                        setUpRecyclerView(trackList.sortedBy { it.listeners.toInt() })
                    }

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchView.setOnSearchClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                inputManger.hideSoftInputFromWindow(searchView.applicationWindowToken, 0)
                trackList = viewModel.getSongs(searchView.query?.toString().orEmpty())
                setUpRecyclerView(trackList.sortedBy { it.listeners.toInt() })

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.sorting_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ascending) {
            order = "ascending"
        }
        if (item.itemId == R.id.descending) {
            order = "descending"
        }
        return super.onOptionsItemSelected(item)
    }


}