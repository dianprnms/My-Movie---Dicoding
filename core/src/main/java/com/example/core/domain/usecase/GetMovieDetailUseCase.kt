package com.example.mymovie.domain.usecase

import com.example.core.domain.model.Detail
import com.example.core.domain.model.MovieDetail
import com.example.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    fun invoke(movieId: Int): Flow<MovieDetail?> {
        return movieRepository.getMovieDetail(movieId)
            .flowOn(Dispatchers.IO)
            .catch { e ->
                emit(null)
            }
    }
}

