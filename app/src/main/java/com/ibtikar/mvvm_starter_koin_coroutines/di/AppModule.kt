package com.ibtikar.mvvm_starter_koin_coroutines.di

import android.content.Context
import com.ibtikar.mvvm_starter_koin_coroutines.common.helpers.ContextProviders
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.SharedPreferencesUtils
import org.koin.dsl.module

val AppModule = module {
    fun provideContext() = ContextProviders()

    fun provideSharedPref(context: Context) = SharedPreferencesUtils(context)

    single { provideContext() }
    single { provideSharedPref(get()) }
}
