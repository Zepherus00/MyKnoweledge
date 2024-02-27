package com.example.cleanarchitecturetestapp.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarchitecturetestapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val dataEditText = findViewById<TextView>(R.id.dataEditText)
        val sendButton = findViewById<TextView>(R.id.sendButton)
        val receiveButton = findViewById<TextView>(R.id.receiveButton)

        viewModel.stateLiveData.observe(this) { state ->
            dataTextView.text = "${state.firstName} ${state.lastName} ${state.saveResult}"
        }

        sendButton.setOnClickListener {
            val text = dataEditText.text.toString()
            viewModel.send(SaveEvent(text))
        }

        receiveButton.setOnClickListener {
            viewModel.send(LoadEvent())
        }
    }
}