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

    private val _resultLive = MutableLiveData<String>()
    val resultLive: LiveData<String> = _resultLive

    fun save(text: String) {
        val params = SaveUserNameParam(name = text)
        val resultData: Boolean = saveUserNameUseCase.execute(param = params)
        _resultLive.value = "Save result = $resultData"
    }

    fun load() {
        val userName = getUserNameUseCase.execute()
        _resultLive.value = "${userName.firstName} ${userName.secondName}"
    }
}