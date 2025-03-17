package com.example.mymovie.domain.usecase

import com.example.core.domain.model.Genre
import com.example.core.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val genreRepository: GenreRepository
) {

    operator fun invoke(): Flow<List<Genre>?> {
        return genreRepository.getGenres()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                emit(emptyList())
            }
    }
}
