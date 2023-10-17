package com.skillbox.lesson_3

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.skillbox.lesson_3.databinding.ActivityMainSecondBinding

class MainActivitySecond : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.email.doOnTextChanged { text, _, _, _ ->
            if (isEmailValid(text)) {
                binding.textInputLayout.isErrorEnabled = false
            } else {
                binding.textInputLayout.error = "Incorrect email"
                binding.textInputLayout.isErrorEnabled = true
        }
        }
    }
}

private fun isEmailValid(email: CharSequence?): Boolean {
    return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}