package com.skillbox.telegram.ui.fragments

import androidx.fragment.app.Fragment
import com.skillbox.telegram.R
import com.skillbox.telegram.utilites.hideKeyboard

class MainFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }
}