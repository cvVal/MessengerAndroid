package com.valpu.seve.messenger.data.remote.repository

import android.content.Context
import com.valpu.seve.messenger.data.local.AppPreferences
import com.valpu.seve.messenger.data.valueobjects.UserListVO
import com.valpu.seve.messenger.data.valueobjects.UserVO
import com.valpu.seve.messenger.service.MessengerAPIService
import io.reactivex.Observable

class UserRepositoryImpl(ctx: Context) : UserRepository {

    private val preferences: AppPreferences = AppPreferences.create(ctx)
    private val service: MessengerAPIService = MessengerAPIService.getInstance()

    override fun findById(id: Long): Observable<UserVO> {

        return service.showUser(id, preferences.accessToken as String)
    }

    override fun all(): Observable<UserListVO> {

        return service.listUsers(preferences.accessToken as String)
    }

    override fun echoDetails(): Observable<UserVO> {

        return service.echoDetails(preferences.accessToken as String)
    }
}