package com.example.datastorelesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastorelesson.ui.theme.DataStoreLessonTheme
import com.example.datastorelesson.ui.theme.Purple40
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreManager = DataStoreManager(this)

        setContent {
            DataStoreLessonTheme {
                val bgColorState = remember {
                    mutableStateOf(Purple40.value)
                }
                val textSizeState = remember {
                    mutableStateOf(10)
                }
                LaunchedEffect(key1 = true) {
                    dataStoreManager.getSettings().collect { settings ->
                        bgColorState.value = settings.bgColor.toULong()
                        textSizeState.value = settings.textSize
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(bgColorState.value)
                ) {
                    MainScreen(dataStoreManager, textSizeState)
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    dataStoreManager: DataStoreManager,
    textSizeState: MutableState<Int>
) {
    val coroutine = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .wrapContentWidth(align = CenterHorizontally)
                .wrapContentHeight(align = CenterVertically)
        ) {
            Text(
                text = "Some text",
                color = Color.White,
                fontSize = textSizeState.value.sp
            )
        }
        Button(onClick = {
            coroutine.launch {
                dataStoreManager.saveSettings(
                    SettingsData(10, Color.Blue.value.toLong())
                )
            }
        }) {
            Text(text = "Blue")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            coroutine.launch {
                dataStoreManager.saveSettings(
                    SettingsData(20, Color.Red.value.toLong())
                )
            }
        }) {
            Text(text = "Red")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            coroutine.launch {
                dataStoreManager.saveSettings(
                    SettingsData(30, Color.Green.value.toLong())
                )
            }
        }) {
            Text(text = "Green")
        }
    }
}