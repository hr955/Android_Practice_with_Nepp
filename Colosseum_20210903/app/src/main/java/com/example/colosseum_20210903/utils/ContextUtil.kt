package com.example.colosseum_20210903.utils

import android.content.Context

class ContextUtil {

    companion object {

        private val prefName = "ColosseumPref"
        private val AUTO_LOGIN = "AUTO_LOGIN"

        fun setAutoLogin(context: Context, isAutoLogin: Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN, isAutoLogin).apply()
        }

        fun getAutoLogin(context: Context): Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            //두번째 파라미터 -> SharedPreferences 가 없는경우 반환할 값
            return pref.getBoolean(AUTO_LOGIN, false)
        }
    }
}