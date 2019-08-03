package com.valpu.seve.messenger.signup

import com.valpu.seve.messenger.auth.AuthInteractor
import com.valpu.seve.messenger.data.local.AppPreferences

class SignUpPresenterImpl(private val view: SignUpView) : SignUpPresenter, SignUpInteractor.OnSignUpFinishedListener,
                    AuthInteractor.OnAuthFinishedListener {

    override var preferences: AppPreferences = AppPreferences.create(view.getContext())

    private val interactor: SignUpInteractor = SignUpInteractorImpl()

    override fun executeSignUp(username: String, phoneNumber: String, password: String) {
        view.showProgress()
        interactor.signUp(username, phoneNumber, password, this)
    }

    override fun onSuccess() {
        interactor.getAuthorization(this)
    }

    override fun onUsernameError() {
        view.hideProgress()
        view.setUsernameError()
    }

    override fun onPasswordError() {
        view.hideProgress()
        view.setPasswordError()
    }

    override fun onPhoneNumberError() {
        view.hideProgress()
        view.setPhoneNumberError()
    }

    override fun onError() {
        view.hideProgress()
        view.showSignUpError()
    }

    override fun onAuthSuccess() {
        interactor.persistAccessToken(preferences)
        interactor.persistUserDetails(preferences)
        view.hideProgress()
        view.navigateToHome()
    }

    override fun onAuthError() {
        view.hideProgress()
        view.showAuthError()
    }
}