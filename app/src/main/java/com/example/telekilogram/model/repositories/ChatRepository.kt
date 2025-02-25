package com.example.telekilogram.model.repositories

import com.example.telekilogram.model.core.Either
import com.example.telekilogram.model.core.RetrofitClient
import com.example.telekilogram.model.models.ChatResponse
import com.example.telekilogram.model.service.ChatService

class ChatRepository {
    private val chatService: ChatService = RetrofitClient.chatService

    suspend fun getChat(chatId: Int): Either<Throwable, List<ChatResponse>> =
        try {
            val response = RetrofitClient.chatService.getChat(chatId)
            Either.Success(response)
        } catch (e: Exception) {
            Either.Error(e)
        }

    suspend fun sendMessage( message: String): Either<Throwable, ChatResponse> =
        try {
            val response = chatService.sendMessage( message = message)
            Either.Success(response)
        } catch (e: Exception) {
            Either.Error(e)
        }

    suspend fun deleteMessage(chatId: Int, messageId: Int) =
        chatService.deleteMessage(chatId, messageId)

    suspend fun updateMessage(chatId: Int, messageId: Int, newMessage: String) =
        chatService.updateMessage(chatId, messageId, newMessage)
}