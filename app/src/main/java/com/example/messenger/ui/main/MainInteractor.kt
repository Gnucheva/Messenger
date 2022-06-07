package com.example.messenger.ui.main

import com.example.messenger.data.vo.ConversationListVO
import com.example.messenger.data.vo.UserListVO

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