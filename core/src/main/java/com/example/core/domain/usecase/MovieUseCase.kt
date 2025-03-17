package com.example.core.domain.usecase

import com.example.core.domain.model.MovieDataDomain
import com.example.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MovieUseCase {
    fun getAllFavoriteMovies(): Flow<List<MovieDataDomain>>
}

class MovieInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {
    override fun getAllFavoriteMovies(): Flow<List<MovieDataDomain>> {
        return movieRepository.getAllFavoriteMovies()
    }
}
