package com.skillbox.telegram.ui.fragments

import androidx.fragment.app.Fragment
import com.skillbox.telegram.utilites.APP_ACTIVITY

open class BaseFragment(layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.appDrawer.disableDrawer()
    }
}