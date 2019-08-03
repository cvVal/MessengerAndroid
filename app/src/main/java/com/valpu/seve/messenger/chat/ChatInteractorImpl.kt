package com.valpu.seve.messenger.chat

import android.content.Context
import com.valpu.seve.messenger.data.local.AppPreferences
import com.valpu.seve.messenger.data.remote.repository.ConversationRepository
import com.valpu.seve.messenger.data.remote.repository.ConversationRepositoryImpl
import com.valpu.seve.messenger.data.remote.request.MessageRequestObject
import com.valpu.seve.messenger.service.MessengerAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChatInteractorImpl(context: Context) : ChatInteractor {

    private val preferences: AppPreferences = AppPreferences.create(context)
    private val service: MessengerAPIService = MessengerAPIService.getInstance()
    private val conversationRepository: ConversationRepository = ConversationRepositoryImpl(context)

    override fun sendMessage(recipientId: Long, message: String,
                             listener: ChatInteractor.OnMessageSendFinishedListener) {

        service.createMessage(
                MessageRequestObject(recipientId, message), preferences.accessToken as String)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _ -> listener.onSendSuccess()},
                        { error ->
                            listener.onSendError()
                            error.printStackTrace()
                        })
    }

    override fun loadMessages(conversationId: Long,
                              listener: ChatInteractor.OnMessageLoadFinishedListener) {

        conversationRepository.findConversationById(conversationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res -> listener.onLoadSuccess(res)},
                        { error ->
                            listener.onLoadError()
                            error.printStackTrace()})
    }
}