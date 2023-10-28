package com.skillbox.lesson_12.ui.main

sealed class State {
    object Loading : State()
    object Success : State()
    data class Error(
        val loginError: String?,
        val passwordError: String?
    ) : State()
}
