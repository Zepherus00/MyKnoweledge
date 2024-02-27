package com.example.cleanarchitecturetestapp.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarchitecturetestapp.R
import com.example.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.domain.models.SaveUserNameParam
import com.example.domain.usecase.GetUserNameUseCase
import com.example.domain.usecase.SaveUserNameUseCase

class MainActivity : AppCompatActivity() {
    private val userStorage by lazy {
        SharedPrefUserStorage(
            context = applicationContext
        )
    }
    private val repository by lazy { com.example.data.repository.UserRepositoryImpl(userStorage = userStorage) }
    private val getUserNameUseCase by lazy {
        GetUserNameUseCase(
            userRepository = repository
        )
    }
    private val saveUserNameUseCase by lazy {
        SaveUserNameUseCase(
            userRepository = repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val dataEditText = findViewById<TextView>(R.id.dataEditText)
        val sendButton = findViewById<TextView>(R.id.sendButton)
        val receiveButton = findViewById<TextView>(R.id.receiveButton)

        sendButton.setOnClickListener {
            val text = dataEditText.text.toString()
            val params = SaveUserNameParam(name = text)
            val result: Boolean = saveUserNameUseCase.execute(param = params)
            dataTextView.text = "Save result = $result"
        }

        receiveButton.setOnClickListener {
            val userName = getUserNameUseCase.execute()
            dataTextView.text = "${userName.firstName} ${userName.secondName}"
        }
    }
}