package com.example.mymovie.domain.usecase

import com.example.core.domain.model.MovieDataDomain
import com.example.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun isMovieFavorited(movieId: Int): Flow<Boolean> {
        return movieRepository.isMovieFavorited(movieId)
    }

    suspend fun addToFavorite(movie: MovieDataDomain) {
        movieRepository.addMovieToFavorite(movie)
    }

    suspend fun removeFromFavorite(movieId: Int) {
        movieRepository.removeMovieFromFavorite(movieId)
    }
}
