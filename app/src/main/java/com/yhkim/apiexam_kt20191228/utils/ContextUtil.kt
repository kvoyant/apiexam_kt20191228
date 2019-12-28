package com.yhkim.apiexam_kt20191228.utils

import android.content.Context

class ContextUtil {
    companion object {
        val prefName = "APIPracticePreference"

        val USER_TOKEN = "USER_TOKEN"

        fun setUserToken(context: Context, inputToken: String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(USER_TOKEN, inputToken).apply()
        }

        fun getUserToken(context: Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(USER_TOKEN, "")!!
        }
    }
}