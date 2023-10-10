package com.example.retrofitlesson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitlesson.databinding.ActivityMainBinding
import com.example.retrofitlesson.signin.AuthRequest
import com.example.retrofitlesson.signin.MainApi
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainAPi = retrofit.create(MainApi::class.java)

        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val user = mainAPi.auth(
                    AuthRequest(
                        binding.userName.text.toString(),
                        binding.password.text.toString()
                    )
                )
                runOnUiThread {
                    Picasso.get().load(user.image).into(binding.iv)
                    binding.firstName.text = user.firstName
                    binding.lastName.text = user.lastName
                }
            }
        }

        /*val text = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val productApi = retrofit.create(ProductApi::class.java)

        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val product = productApi.getProductById(1)
                runOnUiThread {
                    text.text = product.title
                }
            }
        }*/
    }
}