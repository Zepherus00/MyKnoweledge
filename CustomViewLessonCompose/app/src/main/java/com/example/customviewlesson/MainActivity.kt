package com.example.customviewlesson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customviewlesson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TestView3.Listener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.testView.listener = this
    }

    override fun onClick(index: Int) {
        binding.tvSelection.text = TextUtils.menuList[index]
    }
}