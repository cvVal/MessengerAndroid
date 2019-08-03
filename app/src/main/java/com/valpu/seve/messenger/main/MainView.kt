package com.valpu.seve.messenger.main

import com.valpu.seve.messenger.activities.MainActivity
import com.valpu.seve.messenger.base.BaseView

interface MainView : BaseView {

    fun showConversationsLoadError()
    fun showContactsLoadError()
    fun showConversationsScreen()
    fun showContactsScreen()
    fun getContactsFragment(): MainActivity.ContactsFragment
    fun getConversationsFragment(): MainActivity.ConversationsFragment
    fun showNoConversations()
    fun navigateToLogin()
    fun navigateToSettings()
}