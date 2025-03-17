package com.example.core.domain.repository

import com.example.core.domain.model.ResultXXX
import kotlinx.coroutines.flow.Flow

interface MovieVideoRepository {
    fun getMovieVideos(movieId: Int): Flow<List<ResultXXX>?>
}
