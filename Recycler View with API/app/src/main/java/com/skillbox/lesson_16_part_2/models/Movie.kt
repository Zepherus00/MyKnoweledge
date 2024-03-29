package com.skillbox.lesson_16_part_2.models

data class Movie(
    val kinopoiskId: Int,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val genres: List<Genre>,
    val premiereRu: String,
    val countries: List<Country>
)

data class Genre(
    val genre: String
)

data class Country(
    val country: String
)