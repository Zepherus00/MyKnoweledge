package com.skillbox.lesson_13

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatImageModel(
    @Json(name = "id") val id: String,
    @Json(name = "url") val url: String
)

/*import com.google.gson.annotations.SerializedName

data class CatImageModel(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)*/
