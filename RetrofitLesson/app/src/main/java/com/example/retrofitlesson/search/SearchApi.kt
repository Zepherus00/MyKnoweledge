package com.example.retrofitlesson.search

import com.example.retrofitlesson.list.Products
import com.example.retrofitlesson.signin.AuthRequest
import com.example.retrofitlesson.signin.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface SearchApi {
    @Headers(
        "Content-Type: application/json"
    )
    @GET("auth/products/search")
    suspend fun getProductsByName(
        @Header("Authorization") token: String,
        @Query("q") name: String
    ): Products

    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest): Response<User>
}