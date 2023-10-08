package com.example.internalstoragelesson

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.internalstoragelesson.ui.theme.InternalStorageLessonTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InternalStorageLessonTheme {
                val textState = remember {
                    mutableStateOf("")
                }
                val context = LocalContext.current
                val coroutine = rememberCoroutineScope()

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = textState.value)
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        coroutine.launch {
                            textState.value = readFile(context)
                        }
                    }) {
                        Text(text = "Read")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        coroutine.launch {
                            save(context)
                        }
                    }) {
                        Text(text = "Safe")
                    }
                }
            }
        }
    }
}

private suspend fun save(context: Context) {
    val text = "Some saved text"
    withContext(Dispatchers.IO) {
        context.openFileOutput("text.txt", Context.MODE_PRIVATE).use {
            it.write(text.toByteArray())
        }
    }
}

private suspend fun readFile(context: Context) = withContext(Dispatchers.IO) {
    try {
        context.openFileInput("test.txt").bufferedReader().useLines { lines ->
            lines.fold("") { allStrings, lastString ->
                "$allStrings\n$lastString"
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
        ""
    }
}