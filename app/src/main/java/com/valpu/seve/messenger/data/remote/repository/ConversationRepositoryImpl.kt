package com.valpu.seve.messenger.data.remote.repository

import android.content.Context
import com.valpu.seve.messenger.data.local.AppPreferences
import com.valpu.seve.messenger.data.valueobjects.ConversationListVO
import com.valpu.seve.messenger.data.valueobjects.ConversationVO
import com.valpu.seve.messenger.service.MessengerAPIService
import io.reactivex.Observable

class ConversationRepositoryImpl(ctx: Context) : ConversationRepository {

    private val preferences: AppPreferences = AppPreferences.create(ctx)
    private val service: MessengerAPIService = MessengerAPIService.getInstance()

    override fun findConversationById(id: Long): Observable<ConversationVO> {

        return service.showConversation(id, preferences.accessToken as String)
    }

    override fun all(): Observable<ConversationListVO> {

        return service.listConversations(preferences.accessToken as String)
    }
}