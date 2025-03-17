package com.example.mymovie.domain.usecase

import com.example.core.domain.model.ReviewsDomain
import com.example.core.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieReviewsUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {

    fun invoke(movieId: Int): Flow<ReviewsDomain?> {
        return reviewRepository.getMovieReviews(movieId)
            .flowOn(Dispatchers.IO)
            .catch { e ->
                emit(null)
            }
    }
}
