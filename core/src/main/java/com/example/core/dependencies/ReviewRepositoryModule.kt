package com.example.core.dependencies

import com.example.core.data.network.FilmAPIService
import com.example.core.data.repository.ReviewRepositoryImpl
import com.example.core.domain.repository.ReviewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReviewRepositoryModule {

    @Provides
    @Singleton
    fun provideReviewRepository(filmAPIService: FilmAPIService): ReviewRepository {
        return ReviewRepositoryImpl(filmAPIService)
    }
}
