package com.example.telekilogram.ui.adapters

import android.annotation.SuppressLint
import com.example.telekilogram.data.models.ChatResponse

class GenericDiffUtil<T : Any> : androidx.recyclerview.widget.DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return when {
            oldItem is ChatResponse && newItem is ChatResponse -> oldItem.id == newItem.id
            else -> false
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return when {
            oldItem is ChatResponse && newItem is ChatResponse -> oldItem == newItem
            else -> false
        }
    }
}