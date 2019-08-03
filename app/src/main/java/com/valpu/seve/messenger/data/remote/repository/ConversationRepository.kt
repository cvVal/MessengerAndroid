package com.valpu.seve.messenger.data.remote.repository

import com.valpu.seve.messenger.data.valueobjects.ConversationListVO
import com.valpu.seve.messenger.data.valueobjects.ConversationVO
import io.reactivex.Observable

interface ConversationRepository {

    fun findConversationById(id: Long): Observable<ConversationVO>
    fun all(): Observable<ConversationListVO>
}