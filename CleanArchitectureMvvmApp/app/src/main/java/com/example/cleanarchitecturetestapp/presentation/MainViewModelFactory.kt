package com.example.cleanarchitecturetestapp.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.domain.usecase.GetUserNameUseCase
import com.example.domain.usecase.SaveUserNameUseCase

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository by lazy {
        UserRepositoryImpl(
            userStorage = SharedPrefUserStorage(
                context = context
            )
        )
    }

    private val getUserNameUseCase by lazy {
        GetUserNameUseCase(
            userRepository = repository
        )
    }

    private val saveUserNameUseCase by lazy {
        SaveUserNameUseCase(
            userRepository = repository
        )
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        ) as T
    }
}