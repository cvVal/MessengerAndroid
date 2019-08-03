package com.valpu.seve.messenger.settings

import android.content.Context
import android.preference.EditTextPreference
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.Toast
import com.valpu.seve.messenger.data.local.AppPreferences
import com.valpu.seve.messenger.data.remote.request.StatusUpdateRequestObject
import com.valpu.seve.messenger.service.MessengerAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileStatusPreference(
        context: Context, attributeSet: AttributeSet) : EditTextPreference(context, attributeSet) {

    private val service: MessengerAPIService = MessengerAPIService.getInstance()
    private val preferences: AppPreferences = AppPreferences.create(context)

    override fun onDialogClosed(positiveResult: Boolean) {

        if (positiveResult) {

            val etStatus = editText

            if (TextUtils.isEmpty(etStatus.text)) {

                Toast.makeText(context, "Status cannot be empty.", Toast.LENGTH_SHORT).show()
            } else{

                val requestObject = StatusUpdateRequestObject(etStatus.text.toString())

                service.updateUserStatus(requestObject, preferences.accessToken as String)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ res -> preferences.storeUserDetails(res)},
                                { error -> Toast.makeText(context,
                                        "Unable to update status. Try again later.",
                                        Toast.LENGTH_LONG).show()
                                error.printStackTrace()})
            }
        }
        super.onDialogClosed(positiveResult)
    }
}