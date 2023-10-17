package com.skillbox.lesson_13

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.thecatapi.com"

object RetrofitServices {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchImageApi: SearchImageApi = retrofit.create(
        SearchImageApi::class.java
    )
}

interface SearchImageApi {
    @GET("/v1/images/search")
    fun getCatImageList(): Call<List<CatImageModel>>
}