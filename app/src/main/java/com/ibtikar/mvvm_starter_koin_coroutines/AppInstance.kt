package com.ibtikar.mvvm_starter_koin_coroutines

import android.app.Application
import com.ibtikar.mvvm_starter_koin_coroutines.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class AppInstance: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AppInstance)
            modules(listOf(repositoryModule, viewModelModule, contextProviderModule ,retrofitModule, apiModule, DBModule))
        }
    }

}