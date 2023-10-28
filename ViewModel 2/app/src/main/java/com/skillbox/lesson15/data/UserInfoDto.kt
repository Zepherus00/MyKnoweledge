package com.skillbox.lesson15.data

import com.skillbox.lesson15.entity.UserInfo

class UserInfoDto(
    override val email: String,
    override val name: String,
    override val surname: String,
    override val phone: String,
    override val bio: String
) : UserInfo {
}