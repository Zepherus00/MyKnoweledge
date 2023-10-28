package com.skillbox.lesson_7.examples.ui.main

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.skillbox.lesson_7.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}