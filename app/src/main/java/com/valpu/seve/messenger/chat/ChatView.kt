package com.valpu.seve.messenger.chat

import com.stfalcon.chatkit.messages.MessagesListAdapter
import com.valpu.seve.messenger.base.BaseView
import com.valpu.seve.messenger.utils.Message

interface ChatView : BaseView {

    interface ChatAdapter {
        fun navigateToChat(recipientName: String, recipientId: Long, conversationId: Long? = null)
    }

    fun showConversationLoadError()
    fun showMessageSendError()
    fun getMessageListAdapter(): MessagesListAdapter<Message>
}