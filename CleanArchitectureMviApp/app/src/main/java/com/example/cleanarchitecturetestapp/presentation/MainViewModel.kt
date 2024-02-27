package com.example.cleanarchitecturetestapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.SaveUserNameParam
import com.example.domain.usecase.GetUserNameUseCase
import com.example.domain.usecase.SaveUserNameUseCase

class MainViewModel(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<MainState>()
    val stateLiveData: LiveData<MainState> = _stateLiveData

    init {
        _stateLiveData.value = MainState(
            saveResult = false,
            firstName = "",
            lastName = ""
        )
    }

    fun send(event: MainEvent) {
        when (event) {
            is SaveEvent -> {
                save(text = event.text)
            }

            is LoadEvent -> {
                load()
            }
        }
    }

    private fun save(text: String) {
        val params = SaveUserNameParam(name = text)
        val resultData: Boolean = saveUserNameUseCase.execute(param = params)
        _stateLiveData.value = MainState(
            saveResult = resultData,
            firstName = stateLiveData.value?.firstName ?: "",
            lastName = stateLiveData.value?.lastName ?: ""
        )
    }

    private fun load() {
        val userName = getUserNameUseCase.execute()
        _stateLiveData.value = MainState(
            saveResult = stateLiveData.value?.saveResult ?: false,
            firstName = userName.firstName,
            lastName = userName.secondName
        )
    }
}