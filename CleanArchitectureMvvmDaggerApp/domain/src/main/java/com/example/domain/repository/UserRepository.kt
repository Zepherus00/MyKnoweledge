package com.example.domain.repository

import com.example.domain.models.SaveUserNameParam
import com.example.domain.models.Username

interface UserRepository {
    fun saveName(saveParam: SaveUserNameParam): Boolean

    fun getName(): Username
}