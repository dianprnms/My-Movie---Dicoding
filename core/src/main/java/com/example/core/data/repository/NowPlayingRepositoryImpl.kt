package com.example.core.data.repository

import com.example.core.data.mapper.ResultXMapper.mapToDomain
import com.example.core.data.network.FilmAPIService
import com.example.core.domain.model.ResultX
import com.example.core.domain.model.mapToDomain
import com.example.core.domain.repository.NowPlayingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NowPlayingRepositoryImpl @Inject constructor(
    private val filmAPIService: FilmAPIService
) : NowPlayingRepository {

    override fun getNowPlayingMovies(): Flow<List<ResultX>?> {
        return flow {
            val response = filmAPIService.getMovieNowPlaying()
            emit(response.results?.map { it.mapToDomain() })
        }
    }

    override fun getMoviesByGenre(genreId: Int): Flow<List<ResultX>?> {
        return flow {
            val response = filmAPIService.getMoviesByGenre(genreId)
            emit(response.results?.map { it.mapToDomain() })
        }
    }
}
