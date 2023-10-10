package com.example.retrofitlesson

import android.os.Bundle
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitlesson.adapter.ProductAdapter
import com.example.retrofitlesson.databinding.ActivityMainBinding
import com.example.retrofitlesson.search.SearchApi
import com.example.retrofitlesson.signin.AuthRequest
import com.example.retrofitlesson.signin.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
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
        val searchApi = retrofit.create(SearchApi::class.java)

//
        var user: User? = null
        CoroutineScope(Dispatchers.IO).launch {
            user = searchApi.auth(
                AuthRequest("", "")
            )
        }
//

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                CoroutineScope(Dispatchers.IO).launch {
                    val list = p0?.let { searchApi.getProductsByName(user?.token ?: "", it) }
                    runOnUiThread {
                        adapter.submitList(list?.products)
                    }
                }
                return true
            }
        })

        // Ниже работа Retrofit с выдачей списка

        /*adapter = ProductAdapter()
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = adapter

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val listAPi = retrofit.create(ListApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val productsList = listAPi.getAllProducts()
            runOnUiThread {
                adapter.submitList(productsList.products)
            }
        }*/

        // Ниже работа Retrofit Sign In

        /*val interceptor = HttpLoggingInterceptor()
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
        }*/

        // Ниже работа простого Retrofit

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