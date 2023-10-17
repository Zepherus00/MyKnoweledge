package com.skillbox.telegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.skillbox.telegram.R
import com.skillbox.telegram.databinding.ContactItemBinding
import com.skillbox.telegram.databinding.FragmentContactsBinding
import com.skillbox.telegram.models.CommonModel
import com.skillbox.telegram.ui.fragments.single_chat.SingleChatFragment
import com.skillbox.telegram.utilites.*
import de.hdodenhof.circleimageview.CircleImageView

class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FirebaseRecyclerAdapter<CommonModel, ContactsHolder>
    private lateinit var refContacts: DatabaseReference
    private lateinit var refUsers: DatabaseReference
    private lateinit var refUsersListener: AppValueEventListener
    private var mapListeners = hashMapOf<DatabaseReference, AppValueEventListener>()

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Контакты"
        initRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        adapter.stopListening()
        mapListeners.forEach {
            it.key.removeEventListener(it.value)
        }
    }

    private fun initRecyclerView() {
        recyclerView = binding.contactsRecyclerView
        refContacts = REF_DATABASE_ROOT.child(NODE_PHONES_CONTACTS).child(CURRENT_UID)

        val options = FirebaseRecyclerOptions.Builder<CommonModel>()
            .setQuery(refContacts, CommonModel::class.java)
            .build()
        adapter = object : FirebaseRecyclerAdapter<CommonModel, ContactsHolder>(options) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsHolder {
                return ContactsHolder(
                    ContactItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun onBindViewHolder(
                holder: ContactsHolder,
                position: Int,
                model: CommonModel
            ) {
                refUsers = REF_DATABASE_ROOT.child(NODE_USERS).child(model.id)
                refUsersListener = AppValueEventListener {
                    val contact = it.getCommonModel()
                    if (contact.fullname.isEmpty()) {
                        holder.name.text = model.fullname
                    }
                    holder.name.text = contact.fullname
                    holder.status.text = contact.state
                    holder.photo.setImageResource(R.drawable.default_photo)
                    holder.itemView.setOnClickListener {
                        replaceFragment(SingleChatFragment(model))
                    }
                }

                refUsers.addValueEventListener(refUsersListener)
                mapListeners[refUsers] = refUsersListener
            }
        }

        recyclerView.adapter = adapter
        adapter.startListening()
    }

    class ContactsHolder(val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.contactFullName
        val status: TextView = binding.contactStatus
        val photo: CircleImageView = binding.contactPhoto
    }
}