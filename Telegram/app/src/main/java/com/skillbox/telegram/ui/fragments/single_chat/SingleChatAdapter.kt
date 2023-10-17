package com.skillbox.telegram.ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.telegram.databinding.MessageItemBinding
import com.skillbox.telegram.models.CommonModel
import com.skillbox.telegram.utilites.CURRENT_UID
import com.skillbox.telegram.utilites.asTime

class SingleChatAdapter : RecyclerView.Adapter<SingleChatHolder>() {

    //    private var listMessagesCache = emptyList<CommonModel>()
    private var listMessagesCache = mutableListOf<CommonModel>()
    private lateinit var diffResult: DiffUtil.DiffResult

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SingleChatHolder {
        return SingleChatHolder(
            MessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        if (listMessagesCache[position].from == CURRENT_UID) {
            holder.blockUserMessage.visibility = ViewGroup.VISIBLE
            holder.blockReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = listMessagesCache[position].text
            holder.chatUserMessageTime.text =
                listMessagesCache[position].timeStamp.toString().asTime()
        } else {
            holder.blockUserMessage.visibility = ViewGroup.GONE
            holder.blockReceivedMessage.visibility = View.VISIBLE
            holder.receivedMessage.text = listMessagesCache[position].text
            holder.receivedMessageTime.text =
                listMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    override fun getItemCount(): Int = listMessagesCache.size

    fun addItemToBottom(
        item: CommonModel,
        onSuccess: () -> Unit
    ) {
        if (!listMessagesCache.contains(item)) {
            listMessagesCache.add(item)
            notifyItemInserted(listMessagesCache.size)
        }
        onSuccess()
    }

    fun addItemToTop(
        item: CommonModel,
        onSuccess: () -> Unit
    ) {
        if (!listMessagesCache.contains(item)) {
            listMessagesCache.add(item)
            listMessagesCache.sortBy { it.timeStamp.toString() }
            notifyItemInserted(0)
        }
        onSuccess()
    }

    /*fun addItem(item: CommonModel) {
        val newList = mutableListOf<CommonModel>()
        newList.addAll(listMessagesCache)
        if (!newList.contains(item)) newList.add(item)
        newList.sortBy { it.timeStamp.toString() }
        diffResult = DiffUtil.calculateDiff(DiffUtilCallback(listMessagesCache, newList))
        diffResult.dispatchUpdatesTo(this)
        listMessagesCache = newList
    }*/
}

class SingleChatHolder(binding: MessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val blockUserMessage: ConstraintLayout = binding.blocUserMessage
    val chatUserMessage: TextView = binding.chatUserMessage
    val chatUserMessageTime: TextView = binding.chatUserMessageTime

    val blockReceivedMessage: ConstraintLayout = binding.blocReceivedMessage
    val receivedMessage: TextView = binding.chatReceivedMessage
    val receivedMessageTime: TextView = binding.chatReceivedMessageTime
}