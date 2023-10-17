package com.skillbox.lesson_11

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

private const val PREF = "pref"
private const val PREF_KEY = "pref_key"
private var localValue: String? = null
private lateinit var prefs: SharedPreferences

class Repository {

    fun getText(context: Context): String {
        return when {
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
            getDataFromSharedPreference(context) != null -> getDataFromSharedPreference(context)!!
            else -> ""
        }
    }

    fun saveText(text: String) {
        localValue = text
        prefs.edit().putString(PREF_KEY, text).apply()
    }

    fun clearText() {
        localValue = null
        prefs.edit {
            remove(PREF_KEY)
        }
    }

    private fun getDataFromSharedPreference(context: Context): String? {
        prefs = context.getSharedPreferences(PREF, MODE_PRIVATE)
        return prefs.getString(PREF_KEY, null)
    }

    private fun getDataFromLocalVariable(): String? {
        return localValue
    }
}