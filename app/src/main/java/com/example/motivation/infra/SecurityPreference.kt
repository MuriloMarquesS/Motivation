package com.example.motivation.infra

import android.content.Context

class SecurityPreference(context: Context) {

    val msharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)

    // salva nome
    fun storeString(key: String, value: String) {
        msharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {

        //retornar uma string vazia caso de erro (?: "")
        return msharedPreferences.getString(key, "") ?: ""
    }

}
