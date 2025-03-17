package com.example.mymovie.domain.usecase

import com.example.core.domain.model.ResultX
import com.example.core.domain.repository.NowPlayingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val movieRepository: NowPlayingRepository
) {

    operator fun invoke(): Flow<List<ResultX>?> {
        return movieRepository.getNowPlayingMovies()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                emit(null)
            }

    }

    fun invokeByGenre(genreId: Int): Flow<List<ResultX>?> {
        return movieRepository.getMoviesByGenre(genreId)
            .flowOn(Dispatchers.IO)
            .catch { e ->
                emit(null)
            }

    }
}
