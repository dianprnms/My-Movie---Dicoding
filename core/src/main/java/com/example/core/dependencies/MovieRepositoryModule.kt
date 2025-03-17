package com.example.core.dependencies

import com.example.core.data.local.room.MovieDAO
import com.example.core.data.network.FilmAPIService
import com.example.core.data.repository.MovieRepositoryImpl
import com.example.core.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieRepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        filmAPIService: FilmAPIService,
        movieDao: MovieDAO
    ): MovieRepository {
        return MovieRepositoryImpl(filmAPIService, movieDao)
    }
}



