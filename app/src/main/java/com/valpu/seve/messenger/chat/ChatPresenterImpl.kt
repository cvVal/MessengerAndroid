package com.valpu.seve.messenger.chat

import android.annotation.SuppressLint
import android.widget.Toast
import com.valpu.seve.messenger.data.valueobjects.ConversationVO
import com.valpu.seve.messenger.utils.Message
import java.text.SimpleDateFormat

class ChatPresenterImpl(val view: ChatView) :
        ChatPresenter,
        ChatInteractor.OnMessageSendFinishedListener,
        ChatInteractor.OnMessageLoadFinishedListener {

    private val interactor: ChatInteractor = ChatInteractorImpl(view.getContext())

    override fun sendMessage(recipientId: Long, message: String) {
        interactor.sendMessage(recipientId, message, this)
    }

    override fun loadMessages(conversationId: Long) {
        interactor.loadMessages(conversationId, this)
    }

    override fun onSendSuccess() {
        Toast.makeText(view.getContext(), "Message sent", Toast.LENGTH_SHORT). show()
    }

    override fun onSendError() {
        view.showMessageSendError()
    }

    @SuppressLint("SimpleDateFormat")
    override fun onLoadSuccess(conversationVO: ConversationVO) {

        val adapter = view.getMessageListAdapter()
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        conversationVO.messages.forEach { message ->
            adapter.addToStart(Message(message.senderId, message.body,
                    dateFormatter.parse(message.createdAt.split(".")[0])), true)
        }
    }

    override fun onLoadError() {
        view.showConversationLoadError()
    }
}