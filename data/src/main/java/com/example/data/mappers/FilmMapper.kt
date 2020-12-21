package com.example.data.mappers

import com.example.data.models.FilmDataModel
import com.example.domain.models.FilmDetailModel
import javax.inject.Inject

class FilmMapper @Inject constructor() {

    fun toFilmDetails(filmDataModel: FilmDataModel) : FilmDetailModel {
        return FilmDetailModel(
            characters = filmDataModel.characters,
            created = filmDataModel.created,
            director = filmDataModel.director,
            edited = filmDataModel.edited,
            episodeId = filmDataModel.episodeId,
            openingCrawl = filmDataModel.openingCrawl,
            planets = filmDataModel.planets,
            producer = filmDataModel.producer,
            releaseDate = filmDataModel.releaseDate,
            species = filmDataModel.species,
            starships = filmDataModel.starships,
            title = filmDataModel.title,
            url = filmDataModel.url,
            vehicles = filmDataModel.vehicles,
            isFavorite = null
        )
    }
}