package com.example.retrofitlesson.difficultResponse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitlesson.databinding.DiffycultMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DifficltActivity : AppCompatActivity() {
    private lateinit var binding: DiffycultMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiffycultMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder().baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val api = retrofit.create(DiffycultApi::class.java)
                val model = api.getWeatherData(
                    "KEY",
                    "City name",
                    "count days",
                    "no",
                    "no"
                )
                binding.textView.text = model.current.temp_c.toString()
                binding.textView2.text = model.location.localtime
            }
        }
    }
}