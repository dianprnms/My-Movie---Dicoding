package com.example.mymovie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Genre
import com.example.mymovie.domain.usecase.GetGenresUseCase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    private val _genreFlow = MutableStateFlow<List<Genre>?>(null)
    val genreFlow: StateFlow<List<Genre>?> get() = _genreFlow

    fun callApiGenres() {
        viewModelScope.launch {
            getGenresUseCase().collect { genres ->
                _genreFlow.emit(genres)
            }
        }
    }
}
