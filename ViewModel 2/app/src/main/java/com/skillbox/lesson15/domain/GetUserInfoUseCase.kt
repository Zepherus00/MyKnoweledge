package com.skillbox.lesson15.domain

import com.skillbox.lesson15.data.LocaleStorageRepository
import com.skillbox.lesson15.data.MainRepository
import com.skillbox.lesson15.entity.UserInfo
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val localeStorageRepository: LocaleStorageRepository
) {

    suspend fun getUserInfo(login: String, password: String): UserInfo {
        val userId = mainRepository.authorize(login, password)
        val userInfo = mainRepository.getUserInfo(userId)
        localeStorageRepository.saveUserInfo(userInfo)
        return userInfo
    }
}