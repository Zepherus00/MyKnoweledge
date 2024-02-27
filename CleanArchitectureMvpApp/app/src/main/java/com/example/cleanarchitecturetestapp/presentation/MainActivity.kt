package com.example.cleanarchitecturetestapp.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarchitecturetestapp.R

class MainActivity : AppCompatActivity(), MainView {

    private val presenter: MainPresenter = MainPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataEditText = findViewById<TextView>(R.id.dataEditText)
        val sendButton = findViewById<TextView>(R.id.sendButton)
        val receiveButton = findViewById<TextView>(R.id.receiveButton)

        sendButton.setOnClickListener {
            val text = dataEditText.text.toString()
            presenter.save(text)
        }

        receiveButton.setOnClickListener {
            presenter.load()
        }
    }

    override fun showResult(text: String) {
        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        dataTextView.text = text
    }
}