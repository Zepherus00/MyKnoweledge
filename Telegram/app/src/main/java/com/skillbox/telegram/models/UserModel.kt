package com.skillbox.telegram.models

data class UserModel(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    var state: String = "",
    var phoneNumber: String = "",
    var photoUrl: String = "",
)