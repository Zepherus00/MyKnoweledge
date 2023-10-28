package com.skillbox.lesson_16_part_2.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PagedMovieList(
    val results: List<Movie>
)