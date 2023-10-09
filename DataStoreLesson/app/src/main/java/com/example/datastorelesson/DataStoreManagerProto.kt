package com.example.datastorelesson

import android.content.Context
import androidx.datastore.dataStore

private val Context.protoDataStore by dataStore("settings.json", SettingsSerializer)

class DataStoreManagerProto(val context: Context) {
    suspend fun saveColor(color: ULong) {
        context.protoDataStore.updateData { data ->
            data.copy(bgColor = color)
        }
    }

    suspend fun saveTextSize(size: Int) {
        context.protoDataStore.updateData { data ->
            data.copy(textSize = size)
        }
    }

    suspend fun saveSettings(settingsData: SettingsDataProto) {
        context.protoDataStore.updateData { _ ->
            settingsData
        }
    }

    fun getSettings() = context.protoDataStore.data
}