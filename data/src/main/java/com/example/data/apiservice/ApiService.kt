package com.example.data.apiservice

import com.example.data.models.FilmDataModel
import com.example.data.models.FilmsDataModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("films/")
    fun getFilms(): Single<FilmsDataModel>

    @GET("films/1/")
    fun getFilmDetails(): Single<FilmDataModel>
}