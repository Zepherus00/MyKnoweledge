package com.example.cleanarchitecturetestapp.di

import com.example.cleanarchitecturetestapp.presentation.MainPresenterImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainPresenterImpl> {
        MainPresenterImpl(
            getUserNameUseCase = get(),
            saveUserNameUseCase = get()
        )
    }
}