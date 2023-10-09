package com.example.datastorelesson

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@Serializable
data class SettingsDataProto(
    val textSize: Int = 40,
    val bgColor: ULong = Color.Red.value
)
