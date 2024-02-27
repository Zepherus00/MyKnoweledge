package com.example.viewmodelsealedclass

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    fun onSignInClick(login: String, password: String) {
        viewModelScope.launch {
            val isLoginEmpty = login.isBlank()
            val isPasswordEmpty = password.isBlank()
            if (isLoginEmpty || isPasswordEmpty) {
                var loginError: String? = null
                if (isLoginEmpty) {
                    loginError = "Login is required"
                }
                var passwordError: String? = null
                if (isPasswordEmpty) {
                    passwordError = "Password is required"
                }
                _state.value = State.Error(loginError, passwordError)
                _error.send("Login or Password not valid")
            } else {
                _state.value = State.Loading
                try {
                    repository.getData()
                    _state.value = State.Success
                } catch (e: Exception) {
                    _error.send(e.message.toString())
                    _state.value = State.Error(null, null)
                }
            }
        }
    }
}