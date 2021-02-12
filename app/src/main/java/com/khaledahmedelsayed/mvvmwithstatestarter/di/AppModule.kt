package com.khaledahmedelsayed.mvvmwithstatestarter.di

import android.content.Context
import com.khaledahmedelsayed.mvvmwithstatestarter.common.helpers.ContextProviders
import com.khaledahmedelsayed.mvvmwithstatestarter.data.local.SharedPreferencesUtils
import org.koin.dsl.module

val AppModule = module {
    fun provideContext() = ContextProviders()

    fun provideSharedPref(context: Context) = SharedPreferencesUtils(context)

    single { provideContext() }
    single { provideSharedPref(get()) }
}
