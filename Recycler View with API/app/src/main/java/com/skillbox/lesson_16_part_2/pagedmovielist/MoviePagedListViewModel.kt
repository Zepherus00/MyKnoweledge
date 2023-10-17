package com.skillbox.lesson_16_part_2.pagedmovielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.skillbox.lesson_16_part_2.models.Movie
import kotlinx.coroutines.flow.*

class MoviePagedListViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    val filterEnabled = MutableStateFlow(false)

    val pagedMovies : Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = 10),
        initialKey = null,
        pagingSourceFactory = { MoviePagingSource() }
    ).flow.cachedIn(viewModelScope)
}