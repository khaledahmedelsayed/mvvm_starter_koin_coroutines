package com.ibtikar.mvvm_starter_koin_coroutines

import android.app.Application
import com.ibtikar.mvvm_starter_koin_coroutines.common.Utils.isDebugBuildType
import com.ibtikar.mvvm_starter_koin_coroutines.common.helpers.ApiPrettyLogger
import com.ibtikar.mvvm_starter_koin_coroutines.di.AppModule
import com.ibtikar.mvvm_starter_koin_coroutines.di.NetworkModule
import com.ibtikar.mvvm_starter_koin_coroutines.di.RepositoryModule
import com.ibtikar.mvvm_starter_koin_coroutines.di.ViewModelModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppInstance : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (isDebugBuildType) {
                // Add Pretty logger with Timber integration
                Logger.addLogAdapter(AndroidLogAdapter(ApiPrettyLogger.formatStrategy))
                Timber.plant(
                    object : Timber.DebugTree() {
                        override fun log(
                            priority: Int,
                            tag: String?,
                            message: String,
                            t: Throwable?
                        ) {
                            Logger.log(priority, tag, message, t)
                        }
                    }
                )
            }

            // Initialize Koin modules
            androidContext(this@AppInstance)
            modules(
                listOf(
                    AppModule,
                    NetworkModule,
                    RepositoryModule,
                    ViewModelModule
                )
            )
        }
    }
}
