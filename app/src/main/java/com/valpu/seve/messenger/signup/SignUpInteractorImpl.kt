package com.valpu.seve.messenger.signup

import android.text.TextUtils
import com.valpu.seve.messenger.auth.AuthInteractor
import com.valpu.seve.messenger.data.local.AppPreferences
import com.valpu.seve.messenger.data.remote.request.LoginRequestObject
import com.valpu.seve.messenger.data.remote.request.UserRequestObject
import com.valpu.seve.messenger.data.valueobjects.UserVO
import com.valpu.seve.messenger.service.MessengerAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpInteractorImpl : SignUpInteractor {

    override lateinit var userDetails: UserVO
    override lateinit var accessToken: String
    override lateinit var submittedUsername: String
    override lateinit var submittedPassword: String

    private val service: MessengerAPIService = MessengerAPIService.getInstance()

    override fun signUp(username: String, phoneNumber: String, password: String, listener: SignUpInteractor.OnSignUpFinishedListener) {

        submittedUsername = username
        submittedPassword = password

        val userRequestObject = UserRequestObject(username, password, phoneNumber)

        when {

            TextUtils.isEmpty(username) -> listener.onUsernameError()
            TextUtils.isEmpty(password) -> listener.onPasswordError()
            TextUtils.isEmpty(phoneNumber) -> listener.onPhoneNumberError()

            else -> {

                service.createUser(userRequestObject)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ res ->
                            userDetails = res
                            listener.onSuccess()},
                                { error ->
                                    listener.onError()
                                    error.printStackTrace()})
            }
        }
    }

    override fun getAuthorization(listener: AuthInteractor.OnAuthFinishedListener) {

        val userRequestObject = LoginRequestObject(submittedUsername, submittedPassword)

        service.login(userRequestObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    accessToken = res.headers()["Authorization"] as String
                    listener.onAuthSuccess()},
                        { error ->
                            listener.onAuthError()
                            error.printStackTrace()})
    }

    override fun persistAccessToken(preferences: AppPreferences) {

        preferences.storeAccessToken(accessToken)
    }

    override fun persistUserDetails(preferences: AppPreferences) {

        preferences.storeUserDetails(userDetails)
    }
}