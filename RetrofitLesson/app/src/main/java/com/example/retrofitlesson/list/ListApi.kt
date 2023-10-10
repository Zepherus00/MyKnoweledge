package com.example.retrofitlesson.list

import retrofit2.http.GET

interface ListApi {
    @GET("products")
    suspend fun getAllProducts(): Products
}