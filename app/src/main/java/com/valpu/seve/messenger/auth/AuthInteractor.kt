package com.valpu.seve.messenger.auth

import com.valpu.seve.messenger.data.local.AppPreferences
import com.valpu.seve.messenger.data.valueobjects.UserVO

interface AuthInteractor {

    var userDetails: UserVO
    var accessToken: String
    var submittedUsername: String
    var submittedPassword: String

    interface OnAuthFinishedListener {
        fun onAuthSuccess()
        fun onAuthError()
        fun onUsernameError()
        fun onPasswordError()
    }

    fun persistAccessToken(preferences: AppPreferences)

    fun persistUserDetails(preferences: AppPreferences)
}