package com.example.core.domain.repository

import com.example.core.domain.model.ReviewsDomain
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    fun getMovieReviews(movieId: Int): Flow<ReviewsDomain?>
}
