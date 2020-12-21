package com.example.cleanarch.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarch.R
import com.example.domain.models.FilmDetailModel
import com.jakewharton.rxbinding2.view.visibility

class FilmsCatalogAdapter(val films: List<FilmDetailModel>, private val context: Context): RecyclerView.Adapter<FilmsCatalogAdapter.FilmsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        return FilmsViewHolder(LayoutInflater.from(context).inflate(R.layout.film_catalog_item,parent,false))
    }

    override fun getItemCount() = films.size

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(films[position])
    }

    class FilmsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_film_title)
        private val releaseDate: TextView = itemView.findViewById(R.id.tv_film_release_date)
        private val director: TextView = itemView.findViewById(R.id.tv_film_director)
        private val producer: TextView = itemView.findViewById(R.id.tv_film_producer)
        private val satisfied: ImageView = itemView.findViewById(R.id.iv_satisfied)

        fun bind(film: FilmDetailModel) {
            title.text = film.title
            releaseDate.text = film.releaseDate
            director.text = film.director
            producer.text = film.producer

        when(film.isFavorite) {
            true -> {
                satisfied.setImageResource(R.drawable.ic_satisfied)
                satisfied.isVisible = true
            }
            false -> {
                satisfied.setImageResource(R.drawable.ic_dissatisfied)
                satisfied.isVisible = true
            }
            null -> {
                satisfied.isVisible = false
            }
        }
        }
    }
}