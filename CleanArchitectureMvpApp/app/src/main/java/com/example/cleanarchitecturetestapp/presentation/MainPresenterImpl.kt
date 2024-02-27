package com.example.cleanarchitecturetestapp.presentation

import com.example.domain.models.SaveUserNameParam
import com.example.domain.usecase.GetUserNameUseCase
import com.example.domain.usecase.SaveUserNameUseCase

class MainPresenterImpl(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val view: MainView
) : MainPresenter {

    override fun save(text: String) {
        val params = SaveUserNameParam(name = text)
        val resultData: Boolean = saveUserNameUseCase.execute(param = params)
        view.showResult("Save result = $resultData")
    }

    override fun load() {
        val userName = getUserNameUseCase.execute()
        view.showResult("${userName.firstName} ${userName.secondName}")
    }
}