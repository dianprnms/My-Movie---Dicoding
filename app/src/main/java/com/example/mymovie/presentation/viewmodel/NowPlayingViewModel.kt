package com.example.mymovie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.ResultX
import com.example.mymovie.domain.usecase.GetNowPlayingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {

    private val _nowPlayingData = MutableStateFlow<List<ResultX>?>(null)
    val nowPlayingData: StateFlow<List<ResultX>?> get() = _nowPlayingData

    fun callApiNowPlayingByGenre(genreId: Int) {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase.invokeByGenre(genreId).collect { movies ->
                _nowPlayingData.emit(movies)
            }
        }
    }
}
