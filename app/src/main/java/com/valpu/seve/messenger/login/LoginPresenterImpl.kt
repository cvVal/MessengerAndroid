package com.valpu.seve.messenger.login

import com.valpu.seve.messenger.auth.AuthInteractor
import com.valpu.seve.messenger.data.local.AppPreferences

class LoginPresenterImpl(private val view: LoginView) : LoginPresenter,
        AuthInteractor.OnAuthFinishedListener, LoginInteractor.OnDetailsRetrievalFinishedListener {

    private val interactor: LoginInteractor = LoginInteractorImpl()
    private val preferences: AppPreferences = AppPreferences.create(view.getContext())

    override fun executeLogin(username: String, password: String) {
        view.showProgress()
        interactor.login(username, password, this)
    }

    override fun onAuthSuccess() {
        interactor.persistAccessToken(preferences)
        interactor.retrieveDetails(preferences, this)
    }

    override fun onAuthError() {
        view.showAuthError()
        view.hideProgress()
    }

    override fun onUsernameError() {
        view.hideProgress()
        view.setUsernameError()
    }

    override fun onPasswordError() {
        view.hideProgress()
        view.setPasswordError()
    }

    override fun onDetailsRetrievalSuccess() {
        interactor.persistUserDetails(preferences)
        view.hideProgress()
        view.navigateToHome()
    }

    override fun onDetailsRetrievalError() {
        interactor.retrieveDetails(preferences, this)
    }
}