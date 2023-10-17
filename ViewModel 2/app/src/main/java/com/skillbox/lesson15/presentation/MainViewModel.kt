package com.skillbox.lesson15.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.lesson15.domain.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUserInfoUseCase: GetUserInfoUseCase) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    fun onSignClick(login: String, password: String) {
        viewModelScope.launch {
            val isLoginEmpty = login.isBlank()
            val isPasswordEmpty = password.isBlank()
            if (isLoginEmpty || isPasswordEmpty) {
                var loginError: String? = null
                if (isLoginEmpty) {
                    loginError = ""
                }
                var passwordError: String? = null
                if (isPasswordEmpty) {
                    passwordError = ""
                }
                _state.value = State.Error(loginError, passwordError)
                _error.send("")
            } else {
                try {
                    _state.value = State.Loading
                    getUserInfoUseCase.getUserInfo(login, password)
                    _state.value = State.Success
                } catch (e: Exception) {
                    _error.send(e.toString())
                    _state.value = State.Error(null, null)
                }
            }
        }
    }
}