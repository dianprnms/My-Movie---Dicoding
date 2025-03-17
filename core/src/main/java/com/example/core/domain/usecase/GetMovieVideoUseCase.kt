package com.example.mymovie.domain.usecase

import com.example.core.domain.model.ResultXXX
import com.example.core.domain.repository.MovieVideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieVideoUseCase @Inject constructor(
    private val movieVideoRepository: MovieVideoRepository
) {

    fun invoke(movieId: Int): Flow<List<ResultXXX>?> {
        return movieVideoRepository.getMovieVideos(movieId)
            .flowOn(Dispatchers.IO)
            .catch { e ->
                emit(null)
            }
    }
}
