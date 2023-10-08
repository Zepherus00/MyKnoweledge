package com.example.daggerhiltlesson

import javax.inject.Inject

/*
class WiFiManager @Inject constructor(private val settings: WiFiSettings) {
    fun connect() {
        settings.openConnection()
    }

    fun sendMessage() {
        settings.writeBytes()
    }
}

class WiFiSettings @Inject constructor() {
    fun openConnection() {

    }

    fun writeBytes() {

    }
}*/

class WiFiManager (private val settings: WiFiSettings) {
    fun connect() {
        settings.openConnection()
    }

    fun sendMessage() {
        settings.writeBytes()
    }
}

class WiFiSettings {
    fun openConnection() {

    }

    fun writeBytes() {

    }
}
