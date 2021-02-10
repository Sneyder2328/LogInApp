package com.sneyder.loginapp.di.data.preferences

import android.content.SharedPreferences
import com.sneyder.loginapp.di.data.preferences.PreferencesHelper
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppPreferencesHelper @Inject constructor(sharedPreferences: SharedPreferences): PreferencesHelper(sharedPreferences){

    companion object {
        const val USER = "user"
        const val ACCESS_TOKEN = "Authorization"
    }

}