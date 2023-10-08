package com.example.daggerhiltlesson

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerhiltlesson.frag.Activity2
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var wifiManager: WiFiManager
    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val settings = WiFiSettings()
        wifiManager = WiFiManager(settings)*/

        wifiManager.connect()
        wifiManager.sendMessage()

//
        Log.d("MyLog", "MainActivity instance id: $wifiManager")
        startActivity(Intent(this, Activity2::class.java))

        mainViewModel.connect()
    }
}