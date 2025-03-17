package com.example.mymovie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.MovieDataDomain
import com.example.core.domain.model.MovieDetail
import com.example.mymovie.domain.usecase.GetMovieDetailUseCase
import com.example.mymovie.domain.usecase.FavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModel() {

    private val _detailData = MutableStateFlow<MovieDetail?>(null)
    val detailData: StateFlow<MovieDetail?> get() = _detailData

    private val _isFavorited = MutableStateFlow(false)
    val isFavorited: StateFlow<Boolean> get() = _isFavorited

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase.invoke(movieId).collect { moviedetail ->
                _detailData.emit(moviedetail)
            }
        }
    }

    fun checkIfFavorited(movieId: Int) {
        viewModelScope.launch {
            favoriteMovieUseCase.isMovieFavorited(movieId).collect { isFav ->
                _isFavorited.emit(isFav)
            }
        }
    }

    fun toggleFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            val currentDetail = _detailData.value
            if (currentDetail != null) {
                if (_isFavorited.value) {
                    favoriteMovieUseCase.removeFromFavorite(movieId)
                } else {
                    val movie = MovieDataDomain(
                        id = currentDetail.id!!,
                        title = currentDetail.title!!,
                        releaseDate = currentDetail.releaseDate!!,
                        posterPath = currentDetail.posterPath!!
                    )
                    favoriteMovieUseCase.addToFavorite(movie)
                }
                checkIfFavorited(movieId)
            }
        }
    }
}
