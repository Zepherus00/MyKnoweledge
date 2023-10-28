package com.example.lottielesson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import com.example.lottielesson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            start.setOnClickListener {
                lottieView.setMinProgress(0.0f)
                lottieView.setMaxProgress(0.5f)
                lottieView.repeatCount = LottieDrawable.INFINITE
                lottieView.repeatMode = LottieDrawable.RESTART
                lottieView.playAnimation()
            }
            stop.setOnClickListener {
                lottieView.pauseAnimation()
                lottieView.cancelAnimation()
            }
            full.setOnClickListener {
                lottieView.setMinProgress(0.0f)
                lottieView.setMaxProgress(1.0f)
                lottieView.repeatCount = 0
                lottieView.repeatMode = LottieDrawable.REVERSE
                lottieView.playAnimation()
            }
        }
    }
}