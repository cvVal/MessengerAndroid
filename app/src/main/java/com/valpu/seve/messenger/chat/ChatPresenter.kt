package com.valpu.seve.messenger.chat

interface ChatPresenter {

    fun sendMessage(recipientId: Long, message: String)
    fun loadMessages(conversationId: Long)
}