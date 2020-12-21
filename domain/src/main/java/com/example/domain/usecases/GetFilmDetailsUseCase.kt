package com.example.domain.usecases

import com.example.domain.models.FilmDetailModel
import com.example.domain.repositories.RemoteRepo
import io.reactivex.Single
import javax.inject.Inject

class GetFilmDetailsUseCase @Inject constructor(private val apiRepo: RemoteRepo) : SingleUseCase<FilmDetailModel> {

    override fun execute(): Single<FilmDetailModel> {
        return apiRepo.getFilmDetails()
    }
}