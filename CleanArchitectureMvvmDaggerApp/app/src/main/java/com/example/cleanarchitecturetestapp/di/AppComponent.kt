package com.example.cleanarchitecturetestapp.di

import com.example.cleanarchitecturetestapp.presentation.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}