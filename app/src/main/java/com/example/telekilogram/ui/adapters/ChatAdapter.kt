package com.example.telekilogram.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.telekilogram.data.models.ChatModel
import com.example.telekilogram.databinding.ChatItemBinding

class ChatAdapter(
) : ListAdapter<ChatModel, ChatAdapter.ViewHolder>(GenericDiffUtil<ChatModel>()) {

    private var onClick: ((chatId: Int) -> Unit)? = null

    fun setOnClickListener(listener: (chatId: Int) -> Unit) {
        this.onClick = listener
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val chatItem = getItem(position)
        with(holder) {
            binding.tvChatName.text = chatItem.chatName

            itemView.setOnClickListener  {
                onClick?.invoke(chatItem.chatId)
            }

        }

    }

    class ViewHolder(val binding: ChatItemBinding) : RecyclerView.ViewHolder(binding.root)

}