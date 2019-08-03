package com.valpu.seve.messenger.data.remote.request

data class UserRequestObject(
        val username: String,
        val password: String,
        val phoneNumber: String = "")