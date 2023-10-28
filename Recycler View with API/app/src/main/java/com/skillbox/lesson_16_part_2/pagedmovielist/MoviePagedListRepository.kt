package com.skillbox.lesson_16_part_2.pagedmovielist

import com.skillbox.lesson_16_part_2.api.retrofit
import com.skillbox.lesson_16_part_2.models.Movie
import kotlinx.coroutines.delay

class MoviePagedListRepository {
    suspend fun getTopList(page: Int): List<Movie> {
        return retrofit.topList(page).results
    }
}