package com.example.messenger.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.messenger.data.vo.UserVO

//Конструктор приватный , чтобы любой класс , использующий AppPreferences, применял для создания экземпляров AppPreferences метод create()
class AppPreferences private constructor() {
    private lateinit var preferences: SharedPreferences

    //Создает и возвращает для приминения новый экземпляр AppPreferences
    companion object {
        private val PREFERENCE_FILE_NAME = "APP_PREFERENCES"
        fun create(context: Context): AppPreferences {
            val appPreferences = AppPreferences()
            appPreferences.preferences = context
                .getSharedPreferences(PREFERENCE_FILE_NAME, 0)
            return appPreferences
        }

    }

    val accessToken: String?
        get() = preferences.getString("ACCESS_TOKEN", null)

    //Функция служит для сохранения токена доступа,полученного с удаленного сервера в локальный файл настроек
    fun storeAccessToken(accessToken: String) {
        preferences.edit().putString("ACCESS_TOKEN", accessToken).apply()
    }

    val userDetails: UserVO
        get(): UserVO {
            //Возвращает экземпляр UserVO содержащий данные пользователя
            return UserVO(
                preferences.getLong("ID", 0),
                preferences.getString("USERNAME", null) ?: "NONE",
                preferences.getString("PHONE_NUMBER", null) ?: "NONE",
                preferences.getString("STATUS", null) ?: "NONE",
                preferences.getString("CREATED_AT", null) ?: "NONE"
            )
        }

    //Сохраняет данные пользователя,переданные UserVO экземпляру класса SharedPreferences
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