package com.example.messenger.ui.main

import com.example.messenger.data.vo.ConversationListVO
import com.example.messenger.data.vo.UserListVO

class MainPresenterImpl(val view: MainView) : MainPresenter,
    MainInteractor.OnLogoutFinishedListener,
    MainInteractor.OnConversationsLoadFinishedListener,
    MainInteractor.OnContactsLoadFinishedListener {
    val interactor: MainInteractor = MainInteractorImpl(view.getContext())

    override fun onConversationsLoadSuccess(conversationListVO: ConversationListVO) {
        if (!conversationListVO.conversations.isEmpty()) {

            val conversationsFragment = view.getConversationsFragment()
            val conversations = conversationsFragment.conversations
            val adapter = conversationsFragment.conversationAdapter
            conversations.clear()
            adapter.notifyDataSetChanged()

            conversationListVO.conversations.forEach() { contact ->
                conversations.add(contact)
                adapter.notifyItemInserted(conversations.size - 1)
            }
        } else {
            view.showNoConversations()
        }
    }

    override fun onConversationsLoadError() {
        view.showConversationLoadError()
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