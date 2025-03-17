package com.example.core.dependencies

import com.example.core.domain.repository.MovieRepository
import com.example.core.domain.usecase.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun provideMovieRepository(): MovieUseCase
}
