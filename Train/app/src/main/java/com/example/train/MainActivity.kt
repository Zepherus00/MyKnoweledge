package com.example.train

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.train.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var login: String = ""
    private var password: String = ""
    private var name: String = ""
    private var lastName: String = ""
    private var patronymic: String = ""
    private var avatarImageId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onSignInClick(view: View) {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("key1", "sign_in")
        resultSignIn.launch(intent)
    }

    fun onSignUpClick(view: View) {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("key1", "sign_up")
        startActivityForResult(intent, 2)
    }

    private val resultSignIn =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val l = data?.getStringExtra("login")
                val p = data?.getStringExtra("password")
            }
        }
}