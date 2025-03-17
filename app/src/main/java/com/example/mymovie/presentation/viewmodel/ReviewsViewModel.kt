package com.example.mymovie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.ReviewsDomain
import com.example.mymovie.domain.usecase.GetMovieReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase
) : ViewModel() {

    private val _reviewsData = MutableStateFlow<ReviewsDomain?>(null)
    val reviewsData: StateFlow<ReviewsDomain?> get() = _reviewsData

    fun getReviews(movieId: Int) {
        viewModelScope.launch {
            getMovieReviewsUseCase.invoke(movieId).collect { reviews ->
                _reviewsData.emit(reviews)
            }
        }
    }
}
