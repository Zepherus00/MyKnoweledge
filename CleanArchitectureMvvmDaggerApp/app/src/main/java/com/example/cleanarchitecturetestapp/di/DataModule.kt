package com.example.cleanarchitecturetestapp.di

import android.content.Context
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.UserStorage
import com.example.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideUserStorage(context: Context): UserStorage {
        return SharedPrefUserStorage(
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideUserRepositoryImpl(userStorage: UserStorage): UserRepository {
        return UserRepositoryImpl(
            userStorage = userStorage
        )
    }
}