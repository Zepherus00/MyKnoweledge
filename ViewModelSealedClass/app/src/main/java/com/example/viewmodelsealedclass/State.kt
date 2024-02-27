package com.example.viewmodelsealedclass

sealed class State {
    object Loading : State()
    object Success : State()
    data class Error(
        val loginError: String?,
        val passwordError: String?
    ) : State()
}