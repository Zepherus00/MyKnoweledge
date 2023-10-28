package com.example.train

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.train.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var state: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        state = intent.getStringExtra("key1") ?: ""
        if (state == "sign_in") {
            with(binding) {
                profile2.visibility = View.INVISIBLE
                name.visibility = View.GONE
                lastName.visibility = View.GONE
                patronymic.visibility = View.GONE
            }
        } else if (state == "sign_up") {

        }
    }

    fun onClickAvatar(view: View) {
        binding.profile2.visibility = View.VISIBLE
    }

    fun onCLickDone(view: View) {
        with(binding) {
            if (state == "sign_up") {
                val intent = Intent()
                intent.putExtra("login", login.text.toString())
                intent.putExtra("password", password.text.toString())
                intent.putExtra("name", name.text.toString())
                intent.putExtra("last_name", lastName.text.toString())
                intent.putExtra("patronymic", patronymic.text.toString())
                intent.putExtra("profile", "profile")

                setResult(RESULT_OK, intent)
                finish()
            } else if (state == "sign_in") {
                val intent = Intent()
                intent.putExtra("login", login.text.toString())
                intent.putExtra("password", password.text.toString())

                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}