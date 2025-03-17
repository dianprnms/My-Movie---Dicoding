package com.example.core.dependencies

import com.example.core.data.network.FilmAPIService
import com.example.core.data.repository.GenreRepositoryImpl
import com.example.core.domain.repository.GenreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GenreRepositoryModule {

    @Provides
    @Singleton
    fun provideGenreRepository(filmAPIService: FilmAPIService): GenreRepository {
        return GenreRepositoryImpl(filmAPIService)
    }
}
