package com.skillbox.lesson15.data

import kotlinx.coroutines.delay
import javax.inject.Inject

class UserInfoDataSource @Inject constructor() {

    val userInfo = UserInfoDto(
        email = "some email",
        name = "Some name",
        surname = "Some surname",
        phone = "Some phone",
        bio = "some bio"
    )

    suspend fun loadUserInfo(userId: Long): UserInfoDto {
        delay(5000)
        return userInfo
    }
}