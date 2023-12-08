package com.skillbox.lesson_17

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.skillbox.lesson_17.databinding.ActivityContactsBinding
import com.skillbox.lesson_17.databinding.ActivityMainBinding

class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding

    private val launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getContacts()
            } else {
                Toast.makeText(this, "permission is not Granted", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermissions()
    }

    private fun checkPermissions() {
        return if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getContacts()
        } else {
            launcher.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    private fun getContacts() {
        val contentUri = ContactsContract.Contacts.CONTENT_URI
        val contactsProjection = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
        )

        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val phoneProjection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        val phoneSelection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?"

        val stringBuilder = StringBuilder()

        contentResolver.query(
            contentUri,
            contactsProjection,
            null,
            null,
            null
        )?.use { cursor ->
            val idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
            val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            val hasPhoneIndex = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)

            while (cursor.moveToNext()) {
                stringBuilder.append(cursor.getString(nameIndex))
                    .append(": ")
                val hasPhone = cursor.getInt(hasPhoneIndex) > 0
                if (hasPhone) {
                    val contactId = cursor.getString(idIndex)
                    contentResolver.query(
                        phoneUri,
                        phoneProjection,
                        phoneSelection,
                        arrayOf(contactId),
                        null
                    )?.use { phoneCursor ->
                        val numberIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        while (phoneCursor.moveToNext()) {
                            stringBuilder.append(phoneCursor.getString(numberIndex)).append(", ")
                        }
                    }

                } else {
                    stringBuilder.append("no phone")
                }
                stringBuilder.append("\n")
            }
        }

        binding.textView.text = stringBuilder.toString()
    }
}