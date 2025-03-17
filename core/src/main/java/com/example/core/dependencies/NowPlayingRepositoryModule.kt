package com.example.core.dependencies
import com.example.core.data.network.FilmAPIService
import com.example.core.data.repository.NowPlayingRepositoryImpl
import com.example.core.domain.repository.NowPlayingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NowPlayingRepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(filmAPIService: FilmAPIService): NowPlayingRepository {
        return NowPlayingRepositoryImpl(filmAPIService)
    }
}
