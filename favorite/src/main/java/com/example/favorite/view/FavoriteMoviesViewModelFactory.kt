package com.example.favorite.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.usecase.MovieUseCase

@Suppress("UNCHECKED_CAST")
class FavoriteMoviesViewModelFactory(
    private val movieUseCase: MovieUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java)) {
            return FavoriteMoviesViewModel(movieUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
