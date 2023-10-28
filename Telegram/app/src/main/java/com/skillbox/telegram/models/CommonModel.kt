package com.skillbox.telegram.models

data class CommonModel(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    var state: String = "",
    var phoneNumber: String = "",
    var photoUrl: String = "",

    var text: String = "",
    var type: String = "",
    var from: String = "",
    var timeStamp: Any = ""
) {

    override fun equals(other: Any?): Boolean {
        return (other as CommonModel).id == id
    }
}