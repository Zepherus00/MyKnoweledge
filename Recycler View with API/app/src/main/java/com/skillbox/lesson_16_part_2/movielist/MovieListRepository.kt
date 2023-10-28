package com.skillbox.lesson_16_part_2.movielist

import com.skillbox.lesson_16_part_2.api.retrofit
import com.skillbox.lesson_16_part_2.models.Movie
import kotlinx.coroutines.delay

class MovieListRepository {
    suspend fun getPremieres(year: Int, month: String): List<Movie> {
        delay(1000)
        return retrofit.movies(year, month).items
    }
}