package com.example.core.domain.repository

import com.example.core.domain.model.Detail
import com.example.core.domain.model.MovieDataDomain
import com.example.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieDetail(movieId: Int): Flow<MovieDetail?>
    fun isMovieFavorited(movieId: Int): Flow<Boolean>
    suspend fun addMovieToFavorite(movie: MovieDataDomain)
    suspend fun removeMovieFromFavorite(movieId: Int)
    fun getAllFavoriteMovies(): Flow<List<MovieDataDomain>>
}
