package com.example.core.data.repository

import com.example.core.data.local.room.MovieDAO
import com.example.core.data.network.FilmAPIService
import com.example.core.data.mapper.DetailMapper.toDomainModel
import com.example.core.data.mapper.MovieMapper.toMovieDataDomain
import com.example.core.data.mapper.MovieMapper.toMovieEntity
import com.example.core.domain.model.MovieDataDomain
import com.example.core.domain.model.MovieDetail
import com.example.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val filmAPIService: FilmAPIService,
    private val movieDao: MovieDAO
) : MovieRepository {

    override fun getMovieDetail(movieId: Int): Flow<MovieDetail?> =
        flow {
            try {
                val response = filmAPIService.getMovieDetail(movieId)
                emit(response.toDomainModel())
            } catch (e: Exception) {
                emit(null)
            }
        }

    override fun isMovieFavorited(movieId: Int): Flow<Boolean> {
        return flow {
            val movie = movieDao.getMovieById(movieId).firstOrNull()
            emit(movie != null)
        }.flowOn(Dispatchers.IO)
    }

    override fun getAllFavoriteMovies(): Flow<List<MovieDataDomain>> {
        return movieDao.getAllMovies()
            .map { movieEntityList ->
                movieEntityList.map { movieEntity ->
                    movieEntity.toMovieDataDomain()
                }
            }
    }

    override suspend fun addMovieToFavorite(movie: MovieDataDomain) {
        withContext(Dispatchers.IO) {
            val movieEntity = movie.toMovieEntity()
            movieDao.insertData(movieEntity)
        }
    }

    override suspend fun removeMovieFromFavorite(movieId: Int) =
        withContext(Dispatchers.IO) {
            movieDao.deleteMovieById(movieId)
        }

}
