package com.example.viewmodelsealedclass

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.viewmodelsealedclass.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val login = binding.login.text.toString()
            val password = binding.password.text.toString()
            viewModel.onSignInClick(login, password)
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    State.Success -> {
                        binding.progress.isVisible = false
                        binding.loginLayout.error = null
                        binding.passwordLayout.error = null
                        binding.button.isEnabled = true
                    }

                    State.Loading -> {
                        binding.progress.isVisible = true
                        binding.loginLayout.error = null
                        binding.passwordLayout.error = null
                        binding.button.isEnabled = false
                    }

                    is State.Error -> {
                        binding.progress.isVisible = false
                        binding.loginLayout.error = state.loginError
                        binding.passwordLayout.error = state.passwordError
                        binding.button.isEnabled = true
                    }
                }

            }
        }

        lifecycleScope.launch {
            viewModel.error.collect { message ->
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}