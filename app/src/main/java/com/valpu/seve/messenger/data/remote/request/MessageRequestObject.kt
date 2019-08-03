package com.valpu.seve.messenger.data.remote.request

data class MessageRequestObject(
        val recipientId: Long,
        val message: String)