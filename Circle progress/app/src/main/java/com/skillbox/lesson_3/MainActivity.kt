package com.skillbox.lesson_3

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.skillbox.lesson_3.databinding.ActivityMainBinding

private const val NONE = -1

class MainActivity : AppCompatActivity() {
    var currentProgress = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun updateProgress() {
            binding.circelProgressBar.progress = currentProgress
            binding.lineProgressBar.progress = currentProgress
        }

        fun showSnackbar(message: String) {
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
        }

        binding.buttonPlus.setOnClickListener {
            if (currentProgress < 100) {
                currentProgress += 10
                updateProgress()
            }
        }

        binding.buttonMinus.setOnClickListener {
            if (currentProgress > 0) {
                currentProgress -= 10
                updateProgress()
            }
        }

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Checked!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Unchecked!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.light.setColorFilter(getColor(R.color.teal_200))
            } else {
                binding.light.setColorFilter(Color.BLACK)
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { _, buttonId ->
            when (buttonId) {
                R.id.rB1 -> showSnackbar("One")
                R.id.rB2 -> showSnackbar("Two")
                R.id.rB3 -> showSnackbar("Three")
                R.id.rB4 -> showSnackbar("Four")
                NONE -> showSnackbar("None")
            }
        }

        binding.button3.setOnClickListener {
            binding.radioGroup.clearCheck()
        }

        binding.logInButton.setOnClickListener {
            val email = binding.emailEdit.text
            val passwoed = binding.passwordEdit.text
            if (email.isNotBlank() && passwoed.isNotBlank()) {
                Toast.makeText(this, "Log in", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
