package com.example.telekilogram.ui.fragments.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.telekilogram.data.models.ChatResponse
import com.example.telekilogram.data.service.ChatServiceImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

class ChatViewModel : ViewModel(), KoinComponent {
    private val apiImplementation: ChatServiceImpl by inject()

    private val _messagesResult = MutableSharedFlow<MessagesResult>()
    val messagesResult: SharedFlow<MessagesResult> get() = _messagesResult.asSharedFlow()

    private suspend fun <T> handleApiResponse(
        result: Result<Response<T>>,
        successAction: suspend (T) -> Unit,
        errorMessage: String
    ) {
        when {
            result.isSuccess && result.getOrNull()?.isSuccessful == true -> {
                result.getOrNull()?.body()?.let { body ->
                    successAction(body)
                }
            }

            else -> _messagesResult.emit(MessagesResult.Error(errorMessage))
        }
    }

    fun getMessages(chatId: Int) {
        viewModelScope.launch {
            val response = apiImplementation.getChatById(chatId)
            handleApiResponse(
                response,
                { _messagesResult.emit(MessagesResult.Success(it)) },
                "Не удалось получить сообщения"
            )
        }
    }

    fun sendMessage(chatId: Int, senderId: Int, receiverId: Int, message: String) {
        viewModelScope.launch {
            val response = apiImplementation.sendMessage(chatId, senderId, receiverId, message)
            handleApiResponse(response, { getMessages(chatId) }, "Не удалось отправить сообщение")
        }
    }

    fun editMessage(chatId: Int, messageId: Int, newMessage: String) {
        viewModelScope.launch {
            val response = apiImplementation.editMessage(chatId, messageId, newMessage)
            handleApiResponse(response, { getMessages(chatId) }, "Не удалось изменить сообщение")

        }
    }

    fun deleteMessage(chatId: Int, messageId: Int) {
        viewModelScope.launch {
            val response = apiImplementation.deleteMessage(chatId, messageId)
            handleApiResponse(response, { getMessages(chatId) }, "Не удалось удалить сообщение")
        }
    }


    @Serializable
    sealed class MessagesResult {
        data class Success(val messages: List<ChatResponse>) : MessagesResult()
        data class Error(val errorMessage: String) : MessagesResult()
    }
}