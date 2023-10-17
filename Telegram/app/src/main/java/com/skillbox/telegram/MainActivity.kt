package com.skillbox.telegram

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.skillbox.telegram.databinding.ActivityMainBinding
import com.skillbox.telegram.ui.fragments.MainFragment
import com.skillbox.telegram.ui.fragments.register.EnterPhoneNumberFragment
import com.skillbox.telegram.ui.objects.AppDrawer
import com.skillbox.telegram.utilites.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var appDrawer: AppDrawer
    lateinit var toolbar: Toolbar

    override fun onStart() {
        super.onStart()
        AppStates.updateState(AppStates.ONLINE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        initFirebase()
        initUser {
            CoroutineScope(Dispatchers.IO).launch {
                initContacts()
            }
            initFields()
            initFunc()
        }
    }

    override fun onStop() {
        super.onStop()
        AppStates.updateState(AppStates.OFFLINE)
    }

    private fun initFunc() {
        setSupportActionBar(toolbar)
        if (AUTH.currentUser != null) {
            appDrawer.create()
            replaceFragment(MainFragment())
        } else {
            replaceFragment(EnterPhoneNumberFragment())
        }
    }

    private fun initFields() {
        toolbar = binding.mainToolbar
        appDrawer = AppDrawer()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(
                APP_ACTIVITY,
                READ_CONTACT
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            initContacts()
        }
    }
}