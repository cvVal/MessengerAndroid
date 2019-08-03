package com.valpu.seve.messenger.data.remote.repository

import com.valpu.seve.messenger.data.valueobjects.UserListVO
import com.valpu.seve.messenger.data.valueobjects.UserVO
import io.reactivex.Observable

interface UserRepository {

    fun findById(id: Long): Observable<UserVO>
    fun all(): Observable<UserListVO>
    fun echoDetails(): Observable<UserVO>
}