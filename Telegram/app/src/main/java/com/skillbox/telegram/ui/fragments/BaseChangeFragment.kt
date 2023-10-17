package com.skillbox.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.skillbox.telegram.R
import com.skillbox.telegram.utilites.APP_ACTIVITY
import com.skillbox.telegram.utilites.hideKeyboard

open class BaseChangeFragment(layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        APP_ACTIVITY.appDrawer.disableDrawer()
        hideKeyboard()
    }

    override fun onStop() {
        hideKeyboard()
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        APP_ACTIVITY.menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> change()
        }
        return super.onOptionsItemSelected(item)
    }

    open fun change() {
    }
}