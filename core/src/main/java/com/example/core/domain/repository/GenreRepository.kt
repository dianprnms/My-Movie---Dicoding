package com.example.core.domain.repository

import com.example.core.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getGenres(): Flow<List<Genre>?>
}
