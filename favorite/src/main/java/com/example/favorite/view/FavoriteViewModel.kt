package com.example.favorite.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.MovieDataDomain
import com.example.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow<List<MovieDataDomain>>(emptyList())
    val favoriteMovies: StateFlow<List<MovieDataDomain>> = _favoriteMovies.asStateFlow()

    init {
        fetchFavoriteMovies()
    }

    fun fetchFavoriteMovies() {
        viewModelScope.launch {
            movieUseCase.getAllFavoriteMovies()
                .collect { movies ->
                    _favoriteMovies.value = movies
                }
        }
    }
}
