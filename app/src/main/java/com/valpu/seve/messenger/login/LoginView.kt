package com.valpu.seve.messenger.login

import com.valpu.seve.messenger.auth.AuthView
import com.valpu.seve.messenger.base.BaseView

interface LoginView : BaseView, AuthView {

    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun setPasswordError()
    fun navigateToSignUp()
    fun navigateToHome()
}