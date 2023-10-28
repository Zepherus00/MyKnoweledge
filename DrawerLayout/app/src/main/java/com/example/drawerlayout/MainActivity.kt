package com.example.drawerlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.drawerlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            nv.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.item1 -> {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                }
                true
            }

            button.setOnClickListener {
                drawer.openDrawer(GravityCompat.START)
            }
        }
    }
}