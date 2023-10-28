package com.skillbox.lesson_11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skillbox.lesson_11.databinding.ActivityMainBinding

private val repository = Repository()

class MainActivity : AppCompatActivity() {
    private lateinit var bg: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bg = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bg.root)

        setText()
        bg.saveButton.setOnClickListener {
            val textFromEditText = bg.editText.text.toString()
            repository.saveText(textFromEditText)
            setText()
        }
        bg.clearButton.setOnClickListener {
            repository.clearText()
            setText()
        }
    }

    private fun setText() {
        bg.textView.text = repository.getText(this)
    }
}