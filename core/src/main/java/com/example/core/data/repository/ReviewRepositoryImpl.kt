package com.example.core.data.repository


import com.example.core.data.network.FilmAPIService
import com.example.core.domain.model.ReviewDomain
import com.example.core.domain.model.ReviewsDomain
import com.example.core.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val filmAPIService: FilmAPIService
) : ReviewRepository {

    override fun getMovieReviews(movieId: Int): Flow<ReviewsDomain?> {
        return flow {
            try {
                val response = filmAPIService.getReviewsMovie(movieId)

                val reviewsDomain = response.results?.map {
                    ReviewDomain(
                        id = it.id.toString(),
                        author = it.author.toString(),
                        content = it.content.toString(),
                        url = it.url.toString()
                    )
                }

                emit(ReviewsDomain(reviewsDomain))
            } catch (e: Exception) {
                // Tangani error jika ada
                emit(null)
            }
        }
    }
}

