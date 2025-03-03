package com.example.telekilogram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.telekilogram.data.models.ChatModel
import com.example.telekilogram.data.models.ReservedChatsEnum
import com.example.telekilogram.databinding.FragmentChatListBinding
import com.example.telekilogram.ui.adapters.ChatAdapter

class ChatListFragment : Fragment() {

    private var _binding: FragmentChatListBinding? = null
    private val binding get() = _binding!!

    private val chatAdapter = ChatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chats = ReservedChatsEnum.entries.map { ChatModel(it.chatId, it.chatName) }
        binding.rvChatList.adapter = chatAdapter
        binding.rvChatList.layoutManager = LinearLayoutManager(requireContext())

        chatAdapter.setOnClickListener { chatId ->
//            findNavController().navigate(ChatListFragmentDirections.actionChatListFragmentToChatFragment(chatId))
        }
        chatAdapter.submitList(chats)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}