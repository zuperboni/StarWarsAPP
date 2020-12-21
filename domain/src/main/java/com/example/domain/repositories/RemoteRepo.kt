package com.example.domain.repositories

import com.example.domain.models.FilmDetailModel
import io.reactivex.Single

interface RemoteRepo {

    fun getFilms(): Single<List<FilmDetailModel>>

    fun getFilmDetails(): Single<FilmDetailModel>
}