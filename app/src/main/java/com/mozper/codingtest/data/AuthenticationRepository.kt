package com.mozper.codingtest.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

class AuthenticationRepository(@ApplicationContext val context: Context) {

    fun logout() {
        context.getSharedPreferences(SHARED, Context.MODE_PRIVATE).edit().putBoolean(AUTH_KEY, false).apply()
    }

    fun login() {
        context.getSharedPreferences(SHARED, Context.MODE_PRIVATE).edit().putBoolean(AUTH_KEY, true).apply()
    }

    fun isLoggedIn(): Boolean {
        return context.getSharedPreferences(SHARED, Context.MODE_PRIVATE).getBoolean(AUTH_KEY, false)
    }

    companion object {
        const val AUTH_KEY = "auth"
        const val SHARED = "shared"
    }
}