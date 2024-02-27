package com.example.domain.usecase

import com.example.domain.models.Username
import com.example.domain.repository.UserRepository

class GetUserNameUseCase(private val userRepository: UserRepository) {
    fun execute(): Username {
        return userRepository.getName()
    }
}