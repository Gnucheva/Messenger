package com.example.messenger.ui.auth

import com.example.messenger.data.local.AppPreferences
import com.example.messenger.data.vo.UserVO

interface AuthInteractor {

    var userDetails: UserVO
    var accessToken: String
    var submittedUsername: String
    var submittedPassword: String

    interface onAuthFinishedListener {
        fun onAuthSuccess()
        fun onAuthError()
        fun onUsernameError()
        fun onPasswordError()
    }

    //Сохраняют токены доступа и сведения о пользователе в экземпляре класса SharedPreferences
    fun persistAccessToken(preferences: AppPreferences)
    fun persistUserDetails(preferences: AppPreferences)
}