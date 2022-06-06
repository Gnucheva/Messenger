package com.example.messenger.data.remote.repository

import android.content.Context
import com.example.messenger.data.local.AppPreferences
import com.example.messenger.data.vo.ConversationListVO
import com.example.messenger.data.vo.ConversationVO
import com.example.messenger.service.MessengerApiService
import io.reactivex.Observable

class ConversationRepositoryImpl(ctx: Context) : ConversationRepository {

    private val preferences: AppPreferences = AppPreferences.create(ctx)
    private val service: MessengerApiService = MessengerApiService.getInstance()

    override fun findConversationById(id: Long): Observable<ConversationVO> {
        return service.showConversation(preferences.accessToken as String, id)
    }

    override fun all(): Observable<ConversationListVO> {
        return service.listConversation(preferences.accessToken as String)
    }

}