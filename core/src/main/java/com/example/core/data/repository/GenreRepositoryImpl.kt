package com.example.core.data.repository

import com.example.core.data.mapper.GenreMapper
import com.example.core.data.mapper.GenreMapper.mapToDomain
import com.example.core.data.network.FilmAPIService
import com.example.core.domain.model.Genre
import com.example.core.domain.model.toDomainModel
import com.example.core.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val filmAPIService: FilmAPIService
) : GenreRepository {

    override fun getGenres(): Flow<List<Genre>?> {
        return flow {
            val response = filmAPIService.getMovieGenres()

            val genreDomainList = response.genres.map { it.mapToDomain() }

            emit(genreDomainList)
        }
    }
}
