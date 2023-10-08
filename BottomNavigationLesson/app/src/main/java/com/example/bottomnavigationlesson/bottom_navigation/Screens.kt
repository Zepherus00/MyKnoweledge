package com.example.bottomnavigationlesson.bottom_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.bottomnavigationlesson.rule_screen.RuleScreen

@Composable
fun Screen1() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        RuleScreen()
    }
}

@Composable
fun Screen2() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Screen 2",
        textAlign = TextAlign.Center
    )
}

@Composable
fun Screen3() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Screen 3",
        textAlign = TextAlign.Center
    )
}

@Composable
fun Screen4() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Screen 4",
        textAlign = TextAlign.Center
    )
}