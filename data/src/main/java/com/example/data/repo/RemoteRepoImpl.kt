package com.example.data.repo

import com.example.data.apiservice.ApiService
import com.example.data.mappers.FilmMapper
import com.example.domain.models.FilmDetailModel
import com.example.domain.repositories.RemoteRepo
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val filmMapper: dagger.Lazy<FilmMapper>
) : RemoteRepo {

    override fun getFilms(): Single<List<FilmDetailModel>> {
        return apiService.getFilms()
            .map { filmsDataModel ->
                filmsDataModel.results.map {
                    filmMapper.get().toFilmDetails(it)
                }
            }
    }

    override fun getFilmDetails(): Single<FilmDetailModel> {
        return apiService.getFilmDetails()
            .map {
                filmMapper.get().toFilmDetails(it)
            }
    }
}