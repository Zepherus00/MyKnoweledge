package com.example.roomdblesson

import android.app.Application
import com.example.roomdblesson.data.MainDb

class App : Application() {
    val database by lazy {
        MainDb.createDataBase(this)
    }
}