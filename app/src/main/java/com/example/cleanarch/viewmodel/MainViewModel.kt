package com.example.cleanarch.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarch.rx.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import androidx.hilt.lifecycle.ViewModelInject
import com.example.domain.models.FilmDetailModel
import com.example.domain.usecases.GetFilmDetailsUseCase
import com.example.domain.usecases.GetFilmsUseCase

class MainViewModel @ViewModelInject constructor(
   private val filmDetailsUseCase: GetFilmDetailsUseCase,
   private val filmsUseCase: GetFilmsUseCase,
   private val schedulers: SchedulersProvider
) : ViewModel() {

    val filmLiveData = MutableLiveData<FilmDetailModel>()
    val filmsLiveData = MutableLiveData<List<FilmDetailModel>>()
    private val compositeDisposable = CompositeDisposable()

    fun getFilms() {
        filmsUseCase.execute()
            .subscribeOn(schedulers.io())
            .subscribe({
                filmsLiveData.postValue(it)
            },{
                Log.i("DEU RUIM", it.message.orEmpty())
            })
    }

    fun getFilmData() {
        filmDetailsUseCase.execute()
            .subscribeOn(schedulers.io())
            .subscribe({
                filmLiveData.postValue(it)
            },{
                Log.i("DEU RUIM", it.message.orEmpty())
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}