package com.skillbox.telegram.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import com.skillbox.telegram.MainActivity
import com.skillbox.telegram.R
import com.skillbox.telegram.databinding.FragmentSettingsBinding
import com.skillbox.telegram.utilites.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val uri: Uri =
        val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE).child(CURRENT_UID)
        path.putFile(uri).addOnCompleteListener {
            if (it.isSuccessful) {
                showToast(getString(R.string.toast_data_update))
            }
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initFields() {
        binding.settingsBio.text = USERModel.bio
        binding.settingsFullname.text = USERModel.fullname
        binding.settingsPhoneNumber.text = USERModel.phoneNumber
        binding.settingsStatus.text = USERModel.state
        binding.settingsUsername.text = USERModel.username
        binding.settingsBtnChangeUsername.setOnClickListener {
            replaceFragment(ChangeUsernameFragment())
        }
        binding.settingsBtnChangeBio.setOnClickListener {
            replaceFragment(ChangeBioFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settingsMenuExit -> {
                AppStates.updateState(AppStates.OFFLINE)
                AUTH.signOut()
                restartActivity()
            }
            R.id.settingsMenuChangeName -> replaceFragment(ChangeNameFragment())
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        APP_ACTIVITY.appDrawer.updateHeader()
    }
}