package com.valpu.seve.messenger.data.local

import android.content.Context
import android.content.SharedPreferences
import com.valpu.seve.messenger.data.valueobjects.UserVO

class AppPreferences private constructor() {

    private lateinit var preferences: SharedPreferences

    companion object {
        private const val PREFERENCE_FILE_NAME = "APP_PREFERENCES"

        fun create(context: Context): AppPreferences {
            val appPreferences = AppPreferences()
            appPreferences.preferences = context
                    .getSharedPreferences(PREFERENCE_FILE_NAME, 0)
            return appPreferences
        }
    }

    val accessToken: String?
        get() = preferences.getString("ACCESS_TOKEN", null)

    fun storeAccessToken(accessToken: String) {
        preferences.edit().putString("ACCESS_TOKEN", accessToken).apply()
    }

    val userDetails: UserVO
        get(): UserVO {
            return UserVO(
                    preferences.getLong("ID", 0),
                    preferences.getString("USERNAME", null),
                    preferences.getString("PHONE_NUMBER", null),
                    preferences.getString("STATUS", null),
                    preferences.getString("CREATED_AT", null)
            )
        }

    fun storeUserDetails(user: UserVO) {
        val editor: SharedPreferences.Editor = preferences.edit()

        editor.putLong("ID", user.id).apply()
        editor.putString("USERNAME", user.username).apply()
        editor.putString("PHONE_NUMBER", user.phoneNumber).apply()
        editor.putString("STATUS", user.status).apply()
        editor.putString("CREATED_AT", user.createdAt).apply()
    }

    fun clear() {
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}