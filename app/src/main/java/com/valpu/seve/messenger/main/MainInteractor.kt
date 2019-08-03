package com.valpu.seve.messenger.main

import com.valpu.seve.messenger.data.valueobjects.ConversationListVO
import com.valpu.seve.messenger.data.valueobjects.UserListVO

interface MainInteractor {

    interface OnConversationsLoadFinishedListener {
        fun onConversationsLoadSuccess(conversationListVO: ConversationListVO)
        fun onConversationsLoadError()
    }

    interface OnContactsLoadFinishedListener {
        fun onContactsLoadSuccess(userListVO: UserListVO)
        fun onContactsLoadError()
    }

    interface OnLogoutFinishedListener {
        fun onLogoutSuccess()
    }

    fun loadContacts(listener: MainInteractor.OnContactsLoadFinishedListener)
    fun loadConversations(listener: MainInteractor.OnConversationsLoadFinishedListener)
    fun logout(listener: MainInteractor.OnLogoutFinishedListener)
}