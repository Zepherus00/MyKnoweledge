package com.skillbox.telegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skillbox.telegram.R
import com.skillbox.telegram.databinding.FragmentChangeBioBinding
import com.skillbox.telegram.utilites.*

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    private var _binding: FragmentChangeBioBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        binding.settingsInputBio.text = USERModel.bio.toEditable()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeBioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun change() {
        super.change()
        val newBio = binding.settingsInputBio.text.toString()
        setBioToDatabase(newBio)
    }
}