package com.skillbox.telegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skillbox.telegram.R
import com.skillbox.telegram.databinding.FragmentChangeNameBinding
import com.skillbox.telegram.utilites.USERModel
import com.skillbox.telegram.utilites.setNameToDatabase
import com.skillbox.telegram.utilites.showToast
import com.skillbox.telegram.utilites.toEditable

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    private var _binding: FragmentChangeNameBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        initFullnameList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun change() {
        val name = binding.settingsInputName.text.toString()
        val surname = binding.settingsInputSurname.text.toString()
        if (name.isEmpty()) {
            showToast(getString(R.string.settings_toast_name_is_empty))
        } else {
            val fullname = "$name $surname"
            setNameToDatabase(fullname)
        }
    }

    private fun initFullnameList() {
        val fullnameList = USERModel.fullname.split(" ")
        if (fullnameList.size > 1) {
            binding.settingsInputName.text = fullnameList[0].toEditable()
            binding.settingsInputSurname.text = fullnameList[1].toEditable()
        } else {
            binding.settingsInputName.text = fullnameList[0].toEditable()
        }
    }
}