package com.valpu.seve.messenger.signup

import com.valpu.seve.messenger.data.local.AppPreferences

interface SignUpPresenter {

    var preferences: AppPreferences

    fun executeSignUp(username: String, phoneNumber: String, password: String)
}