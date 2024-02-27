package com.example.data.storage.sharedprefs

import android.content.Context
import com.example.data.storage.UserStorage
import com.example.data.storage.models.User

private const val SHARED_PREF_NAME = "shared_pref_key"
private const val KEY_FIRST_NAME = "first_name"
private const val KEY_LAST_NAME = "last_name"
private const val DEFAULT_FIRST_NAME = "default first name"
private const val DEFAULT_LAST_NAME = "default last name"

class SharedPrefUserStorage(context: Context) : UserStorage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override fun save(user: User): Boolean {
        sharedPreferences.edit().putString(KEY_FIRST_NAME, user.firstName).apply()
        sharedPreferences.edit().putString(KEY_LAST_NAME, user.lastName).apply()
        return true
    }

    override fun getName(): User {
        val firstName =
            sharedPreferences.getString(KEY_FIRST_NAME, DEFAULT_FIRST_NAME) ?: DEFAULT_FIRST_NAME
        val lastName =
            sharedPreferences.getString(KEY_LAST_NAME, DEFAULT_LAST_NAME) ?: DEFAULT_LAST_NAME
        return User(firstName = firstName, lastName = lastName)
    }
}