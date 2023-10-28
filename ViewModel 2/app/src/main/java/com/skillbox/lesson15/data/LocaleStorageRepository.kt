package com.skillbox.lesson15.data

import com.skillbox.lesson15.entity.UserInfo
import kotlinx.coroutines.delay
import javax.inject.Inject

class LocaleStorageRepository @Inject constructor() {

    suspend fun saveUserInfo(userInfo: UserInfo) {
        delay(5000)
    }
}