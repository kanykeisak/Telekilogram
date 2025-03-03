package com.example.telekilogram.data.service

import com.example.telekilogram.data.models.ChatResponse
import com.example.telekilogram.utils.safeApiCall
import org.koin.core.component.KoinComponent
import retrofit2.Response

class ChatServiceImpl (private val apiService: ChatService) : KoinComponent {
    suspend fun getChatById(chatId: Int): Result<Response<List<ChatResponse>>> =
        safeApiCall(
            { apiService.getChatById(chatId) },
            "Error fetching getChatById"
        )

    suspend fun sendMessage(
        chatId: Int,
        senderId: Int,
        receiverId: Int,
        message: String
    ): Result<Response<ChatResponse>> =
        safeApiCall(
            { apiService.sendMessage(chatId, senderId, receiverId, message) },
            "Error fetching send message"
        )

    suspend fun editMessage(
        chatId: Int,
        messageId: Int,
        newMessage: String
    ): Result<Response<ChatResponse>> =
        safeApiCall(
            { apiService.editMessage(chatId, messageId, newMessage) },
            "Error fetching edit message"
        )

    suspend fun deleteMessage(
        chatId: Int,
        messageId: Int
    ): Result<Response<Unit>> =
        safeApiCall(
            { apiService.deleteMessage(chatId, messageId) },
            "Error fetching delete message"
        )
}