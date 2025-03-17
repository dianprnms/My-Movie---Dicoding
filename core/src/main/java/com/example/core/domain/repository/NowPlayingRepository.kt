package com.example.core.domain.repository

import com.example.core.domain.model.ResultX
import kotlinx.coroutines.flow.Flow

interface NowPlayingRepository {
    fun getNowPlayingMovies(): Flow<List<ResultX>?>
    fun getMoviesByGenre(genreId: Int): Flow<List<ResultX>?>
}
