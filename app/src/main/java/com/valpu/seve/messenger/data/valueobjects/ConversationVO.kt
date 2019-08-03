package com.valpu.seve.messenger.data.valueobjects

data class ConversationVO(
        val conversationId: Long,
        val secondPartyUsername: String,
        val messages: ArrayList<MessageVO>)