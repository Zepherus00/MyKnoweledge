package com.example.cleanarchitecturetestapp.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecturetestapp.R
import com.example.cleanarchitecturetestapp.app.App
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]

        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val dataEditText = findViewById<TextView>(R.id.dataEditText)
        val sendButton = findViewById<TextView>(R.id.sendButton)
        val receiveButton = findViewById<TextView>(R.id.receiveButton)

        viewModel.resultLive.observe(this) {
            dataTextView.text = it
        }

        sendButton.setOnClickListener {
            val text = dataEditText.text.toString()
            viewModel.save(text)
        }

        receiveButton.setOnClickListener {
            viewModel.load()
        }
    }
}