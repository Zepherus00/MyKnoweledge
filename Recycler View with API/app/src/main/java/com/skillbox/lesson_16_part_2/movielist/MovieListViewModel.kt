package com.skillbox.lesson_16_part_2.movielist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.lesson_16_part_2.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieListViewModel private constructor(
    private val repository: MovieListRepository
) : ViewModel() {
    constructor() : this(MovieListRepository())

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    val filterEnabled = MutableStateFlow(false)

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> =
        combine(_movies, filterEnabled) { movies, filterEnabled ->
            if (filterEnabled) movies.filter { movie ->
                movie.countries.any { it.country == "Россия" }
            }
            else movies
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = _movies.value
        )

    init {
        loadPremieres()
    }

    private fun loadPremieres() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                repository.getPremieres(2022, "JANUARY")
            }.fold(
                onSuccess = { _movies.value = it },
                onFailure = { Log.d("MovieListViewModel", it.message ?: "") }
            )
            _isLoading.value = false
        }
    }

    fun refresh() {
        loadPremieres()
    }
}