package com.valpu.seve.messenger.utils

import com.stfalcon.chatkit.commons.models.IUser

class Author(
        val id: Long,
        val username: String) : IUser {

    override fun getAvatar(): String? {
        return null
    }

    override fun getName(): String {
        return username
    }

    override fun getId(): String {
        return id.toString()
    }
}