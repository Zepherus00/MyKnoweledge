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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastorelesson.ui.theme.DataStoreLessonTheme
import kotlinx.coroutines.launch

class MainActivityProto : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreManager = DataStoreManagerProto(this)

        setContent {
            DataStoreLessonTheme {
                val settingState =
                    dataStoreManager.getSettings().collectAsState(initial = SettingsDataProto())

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(settingState.value.bgColor)
                ) {
                    MainScreenProto(dataStoreManager, settingState.value.textSize)
                }
            }
        }
    }
}

@Composable
fun MainScreenProto(
    dataStoreManager: DataStoreManagerProto,
    textSizeState: Int
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
                fontSize = textSizeState.sp
            )
        }
        Button(onClick = {
            coroutine.launch {
                dataStoreManager.saveSettings(
                    SettingsDataProto(10, Color.Blue.value)
                )
            }
        }) {
            Text(text = "Blue")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            coroutine.launch {
                dataStoreManager.saveSettings(
                    SettingsDataProto(20, Color.Red.value)
                )
            }
        }) {
            Text(text = "Red")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            coroutine.launch {
                dataStoreManager.saveSettings(
                    SettingsDataProto(30, Color.Green.value)
                )
            }
        }) {
            Text(text = "Green")
        }
    }
}