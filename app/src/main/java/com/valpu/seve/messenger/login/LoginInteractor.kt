package com.valpu.seve.messenger.login

import com.valpu.seve.messenger.auth.AuthInteractor
import com.valpu.seve.messenger.data.local.AppPreferences

interface LoginInteractor : AuthInteractor {

    interface OnDetailsRetrievalFinishedListener {
        fun onDetailsRetrievalSuccess()
        fun onDetailsRetrievalError()
    }

    fun login(username: String, password: String, listener: AuthInteractor.OnAuthFinishedListener)
    fun retrieveDetails(preferences: AppPreferences, listener: OnDetailsRetrievalFinishedListener)
}