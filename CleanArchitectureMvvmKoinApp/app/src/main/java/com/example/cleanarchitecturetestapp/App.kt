package com.example.cleanarchitecturetestapp

import android.app.Application
import com.example.cleanarchitecturetestapp.di.appModule
import com.example.cleanarchitecturetestapp.di.dataModule
import com.example.cleanarchitecturetestapp.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}