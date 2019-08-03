package com.valpu.seve.messenger.base

import android.content.Context

interface BaseView {

    fun bindViews()
    fun getContext(): Context
}