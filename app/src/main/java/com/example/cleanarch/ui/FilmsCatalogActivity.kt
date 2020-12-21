package com.example.cleanarch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarch.R
import com.example.cleanarch.ui.adapter.FilmsCatalogAdapter
import com.example.cleanarch.ui.adapter.SwipeCallback
import com.example.cleanarch.viewmodel.MainViewModel
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmsCatalogActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var recycler: RecyclerView
    private lateinit var filmsCatalogAdapter: FilmsCatalogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.films_catalog_activity)
        recycler = findViewById(R.id.recycler_films)
        viewModel.getFilms()

        viewModel.filmsLiveData.observe(this, Observer { films ->
            films?.let {
                filmsCatalogAdapter = FilmsCatalogAdapter(films,this)
                recycler.adapter = filmsCatalogAdapter
                recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
                val itemTouchHelper = ItemTouchHelper(
                    SwipeCallback(
                        this,
                        filmsCatalogAdapter,
                        ::swipeToLeft,
                        ::swipeToRight))
                itemTouchHelper.attachToRecyclerView(recycler)
            }
        })
    }

    private fun swipeToLeft(viewHolder: RecyclerView.ViewHolder) {
        val position = viewHolder.adapterPosition
        val film = filmsCatalogAdapter.films[position]

        film.isFavorite = false
        filmsCatalogAdapter.notifyItemChanged(position)
    }

    private fun swipeToRight(viewHolder: RecyclerView.ViewHolder) {
        val position = viewHolder.adapterPosition
        val film = filmsCatalogAdapter.films[position]

        film.isFavorite = true
        filmsCatalogAdapter.notifyItemChanged(position)
    }
}