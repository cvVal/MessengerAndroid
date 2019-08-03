package com.valpu.seve.messenger.main

import com.valpu.seve.messenger.data.valueobjects.ConversationListVO
import com.valpu.seve.messenger.data.valueobjects.UserListVO

class MainPresenterImpl(val view: MainView) : MainPresenter,
        MainInteractor.OnContactsLoadFinishedListener,
        MainInteractor.OnConversationsLoadFinishedListener,
        MainInteractor.OnLogoutFinishedListener {

    private val interactor: MainInteractor = MainInteractorImpl(view.getContext())

    override fun onConversationsLoadSuccess(conversationListVO: ConversationListVO) {

        if (conversationListVO.conversations.isNotEmpty()) {
            val conversationFragment = view.getConversationsFragment()
            val conversations = conversationFragment.conversations
            val adapter = conversationFragment.conversationsAdapter
            conversations.clear()
            adapter.notifyDataSetChanged()
            conversationListVO.conversations.forEach { contact ->
                conversations.add(contact)
                adapter.notifyItemInserted(conversations.size - 1)
            }
        } else {
            view.showNoConversations()
        }
    }

    override fun onConversationsLoadError() {
        view.showConversationsLoadError()
    }

    override fun onContactsLoadSuccess(userListVO: UserListVO) {

        val contactsFragment = view.getContactsFragment()
        val contacts = contactsFragment.contacts
        val adapter = contactsFragment.contactsAdapter
        contacts.clear()
        adapter.notifyDataSetChanged()
        userListVO.users.forEach { contact ->
            contacts.add(contact)
            contactsFragment.contactsAdapter.notifyItemInserted(contacts.size - 1)
        }
    }

    override fun onContactsLoadError() {
        view.showContactsLoadError()
    }

    override fun onLogoutSuccess() {
        view.navigateToLogin()
    }

    override fun loadConversations() {
        interactor.loadConversations(this)
    }

    override fun loadContacts() {
        interactor.loadContacts(this)
    }

    override fun executeLogout() {
        interactor.logout(this)
    }
}