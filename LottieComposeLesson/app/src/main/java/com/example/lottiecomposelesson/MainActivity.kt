package com.example.lottiecomposelesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.lottiecomposelesson.ui.theme.LottieComposeLessonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottieComposeLessonTheme {
                val composition by rememberLottieComposition(
                    spec = LottieCompositionSpec.Asset("anim.json")
                )
                var isPlaying by remember {
                    mutableStateOf(false)
                }
                val animSpec = LottieClipSpec.Progress(0.5f, 0.9f)

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnimation(
                        composition = composition,
                        isPlaying = isPlaying,
                        speed = 3f,
                        iterations = LottieConstants.IterateForever,
                        reverseOnRepeat = true,
                        clipSpec = animSpec
                    )
                    Button(onClick = {
                        isPlaying = true
                    }) {
                        Text(text = "Play anim")
                    }
                }
            }
        }
    }
}