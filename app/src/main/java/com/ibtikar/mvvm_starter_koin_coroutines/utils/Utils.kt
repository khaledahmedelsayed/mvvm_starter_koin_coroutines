package com.ibtikar.mvvm_starter_koin_coroutines.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import org.koin.core.KoinComponent
import org.koin.core.inject

object Utils : KoinComponent {

    private val context: Context by inject()

    fun isConnectedToNetwork(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            } else {
                connectivityManager.activeNetworkInfo
            }
        if (capabilities != null) {
            return true //capabilities.hasTransport(NetworkCapabilities.) --> To check Network type (wifi/cellular/ethernet)
        }
        return false
    }
}