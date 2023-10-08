package com.example.daggerhiltlesson

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object MainModule {
    @Provides
    @ActivityRetainedScoped
    fun provideWiFiManager(settings: WiFiSettings): WiFiManager {
         return WiFiManager(settings)
    }

    @Provides
    fun provideWiFiSettings(): WiFiSettings {
        return WiFiSettings()
    }
}