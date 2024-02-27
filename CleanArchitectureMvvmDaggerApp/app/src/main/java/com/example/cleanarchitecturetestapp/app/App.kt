package com.example.cleanarchitecturetestapp.app

import android.app.Application
import com.example.cleanarchitecturetestapp.di.AppComponent
import com.example.cleanarchitecturetestapp.di.AppModule
import com.example.cleanarchitecturetestapp.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}