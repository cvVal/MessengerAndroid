package com.valpu.seve.messenger.signup

import com.valpu.seve.messenger.auth.AuthView
import com.valpu.seve.messenger.base.BaseView

interface SignUpView : BaseView, AuthView {

    fun showProgress()
    fun showSignUpError()
    fun hideProgress()
    fun setUsernameError()
    fun setPhoneNumberError()
    fun setPasswordError()
    fun navigateToHome()
}