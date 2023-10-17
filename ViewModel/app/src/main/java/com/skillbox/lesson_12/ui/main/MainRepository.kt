package com.skillbox.lesson_12.ui.main

import kotlinx.coroutines.delay
import java.net.ConnectException

class MainRepository {

    private var count = 0

    suspend fun getData(): String {
        delay(5000)
        return if (count++ % 2 == 0) {
            throw ConnectException("")
        } else {
            "Done"
        }
    }
}