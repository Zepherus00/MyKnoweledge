package com.example.data.repository

import com.example.data.storage.UserStorage
import com.example.data.storage.models.User
import com.example.domain.models.SaveUserNameParam
import com.example.domain.models.Username
import com.example.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun saveName(saveParam: SaveUserNameParam): Boolean {

        val user = mapToStorage(saveParam)

        val result = userStorage.save(user)
        return result
    }

    override fun getName(): Username {
        val user = userStorage.getName()
        return mapToDomain(user)
    }

    private fun mapToStorage(saveParam: SaveUserNameParam): User {
        return User(firstName = saveParam.name, lastName = "")
    }

    private fun mapToDomain(user: User): Username {
        return Username(firstName = user.firstName, lastName = user.lastName)
    }
}