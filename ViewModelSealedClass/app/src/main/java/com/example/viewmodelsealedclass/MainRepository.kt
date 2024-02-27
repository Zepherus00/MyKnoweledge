package com.example.viewmodelsealedclass

import kotlinx.coroutines.delay
import java.net.ConnectException

class MainRepository {
    private var count = 0

    suspend fun getData(): String {
        delay(5_000)
        return if (count % 2 == 0) {
            throw ConnectException("No connection")
        } else {
            "DOne"
        }
    }
}