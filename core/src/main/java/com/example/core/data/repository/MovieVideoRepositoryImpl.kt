package com.example.core.data.repository

import com.example.core.data.mapper.GenreMapper.mapToDomain
import com.example.core.data.mapper.ResultXXXMapper.mapToDomain
import com.example.core.data.network.FilmAPIService
import com.example.core.domain.model.ResultXXX
import com.example.core.domain.repository.MovieVideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieVideoRepositoryImpl @Inject constructor(
    private val filmAPIService: FilmAPIService
) : MovieVideoRepository {

    override fun getMovieVideos(movieId: Int): Flow<List<ResultXXX>?> {
        return flow {
            try {
                val response = filmAPIService.getVideo(movieId)

                val resultList = response.results?.map { it.mapToDomain() }

                emit(resultList)
            } catch (e: Exception) {
                emit(null)
            }
        }
    }
}

