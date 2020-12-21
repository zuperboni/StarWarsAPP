package com.example.domain.usecases

import com.example.domain.models.FilmDetailModel
import com.example.domain.repositories.RemoteRepo
import io.reactivex.Single
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(private val apiRepo: RemoteRepo) : SingleUseCase<List<FilmDetailModel>>{

    override fun execute(): Single<List<FilmDetailModel>> {
        return apiRepo.getFilms()
    }
}