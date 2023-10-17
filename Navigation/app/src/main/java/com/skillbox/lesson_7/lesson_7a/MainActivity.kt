package com.skillbox.lesson_7.lesson_7a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.skillbox.lesson_7.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            replace<BlankFragment>(R.id.fragment_container)
            addToBackStack(BlankFragment::class.java.simpleName)
        }
    }
}