package com.example.core.dependencies

import com.example.core.data.network.FilmAPIService
import com.example.core.data.repository.MovieVideoRepositoryImpl
import com.example.core.domain.repository.MovieVideoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieVideoRepositoryModule {

    @Provides
    @Singleton
    fun provideMovieVideoRepository(filmAPIService: FilmAPIService): MovieVideoRepository {
        return MovieVideoRepositoryImpl(filmAPIService)
    }
}
