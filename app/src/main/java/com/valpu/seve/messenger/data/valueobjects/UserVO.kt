package com.valpu.seve.messenger.data.valueobjects

data class UserVO(
        val id: Long,
        val username: String,
        val phoneNumber: String,
        val status: String,
        val createdAt: String)