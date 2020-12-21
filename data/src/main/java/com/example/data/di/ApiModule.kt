package com.example.data.di

import com.example.data.apiservice.ApiService
import com.example.data.repo.RemoteRepoImpl
import com.example.domain.repositories.RemoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(ApplicationComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRemoteRepo(remoteRepo: RemoteRepoImpl): RemoteRepo = remoteRepo
}