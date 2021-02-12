package com.ibtikar.mvvm_starter_koin_coroutines.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.ibtikar.mvvm_starter_koin_coroutines.common.extensions.get
import com.ibtikar.mvvm_starter_koin_coroutines.common.extensions.set
import com.ibtikar.mvvm_starter_koin_coroutines.common.types.SharedPrefsKeys
import org.koin.core.KoinComponent

class SharedPreferencesUtils(context: Context) :
    KoinComponent {
    val mPrefs: SharedPreferences = context.getSharedPreferences(SharedPrefsKeys.SHARED_PREFERENCES.name, MODE_PRIVATE)
}

fun SharedPreferencesUtils.set(key: String, value: Any?) {
    this.mPrefs[key] = value
}

inline fun <reified T : Any> SharedPreferencesUtils.get(key: String, defaultValue: T? = null) =
    this.mPrefs[key, defaultValue]
