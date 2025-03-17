package com.example.mymovie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.ResultXXX
import com.example.mymovie.domain.usecase.GetMovieVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val getMovieVideosUseCase: GetMovieVideoUseCase
) : ViewModel() {

    private val _videoData = MutableStateFlow<List<ResultXXX>?>(null)
    val videoData: StateFlow<List<ResultXXX>?> get() = _videoData

    fun getVideo(movieId: Int) {
        viewModelScope.launch {
            getMovieVideosUseCase.invoke(movieId).collect { videos ->
                _videoData.emit(videos)
            }
        }
    }
}
