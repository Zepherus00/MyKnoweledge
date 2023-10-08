package com.example.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun DrawerMenu(onEvent: (DrawerEvents) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.drawer_list_bg),
            contentDescription = "BG",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier.fillMaxSize()
        ) {
            DrawerHeader()
            DrawerBody { event ->
                onEvent(event)
            }
        }
    }
}