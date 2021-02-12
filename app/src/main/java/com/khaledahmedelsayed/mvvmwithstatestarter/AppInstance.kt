package com.khaledahmedelsayed.mvvmwithstatestarter

import android.app.Application
import com.khaledahmedelsayed.mvvmwithstatestarter.common.Utils.isDebugBuildType
import com.khaledahmedelsayed.mvvmwithstatestarter.common.helpers.ApiPrettyLogger
import com.khaledahmedelsayed.mvvmwithstatestarter.di.AppModule
import com.khaledahmedelsayed.mvvmwithstatestarter.di.NetworkModule
import com.khaledahmedelsayed.mvvmwithstatestarter.di.RepositoryModule
import com.khaledahmedelsayed.mvvmwithstatestarter.di.ViewModelModule
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
