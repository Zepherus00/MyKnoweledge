package com.skillbox.telegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skillbox.telegram.R
import com.skillbox.telegram.databinding.FragmentChangeUsernameBinding
import com.skillbox.telegram.utilites.*

class ChangeUsernameFragment : BaseChangeFragment(R.layout.fragment_change_username) {

    private var _binding: FragmentChangeUsernameBinding? = null
    private val binding get() = _binding!!

    lateinit var newUsername: String

    override fun onResume() {
        super.onResume()
        binding.settingsInputUsername.text = USERModel.username.toEditable()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeUsernameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun change() {
        newUsername = binding.settingsInputUsername.text.toString().lowercase()
        if (newUsername.isEmpty()) {
            showToast("Поле пустое")
        } else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener {
                    if (it.hasChild(newUsername)) {
                        showToast("Такой пользователь уже существует")
                    } else {
                        changeUsername()
                    }
                })
        }
    }

    private fun changeUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(newUsername).setValue(CURRENT_UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUsername(newUsername)
                }
            }
    }
}