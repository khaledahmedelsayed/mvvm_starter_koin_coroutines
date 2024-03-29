package com.ibtikar.mvvm_starter_koin_coroutines.common

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.lifecycle.MutableLiveData
import com.ibtikar.mvvm_starter_koin_coroutines.BuildConfig
import com.ibtikar.mvvm_starter_koin_coroutines.common.Constants.Debug.DEBUG_BUILD_TYPE
import com.ibtikar.mvvm_starter_koin_coroutines.common.extensions.toStringOrNull
import com.ibtikar.mvvm_starter_koin_coroutines.common.types.ErrorType
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

object Utils : KoinComponent {

    private val context: Context by inject()
    var isDebugBuildType = BuildConfig.BUILD_TYPE == DEBUG_BUILD_TYPE

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
            return true
        }
        // capabilities.hasTransport(NetworkCapabilities.) --> To check Network type (wifi/cellular/ethernet)
        return false
    }

    fun getCoroutineExceptionHandler(internalState: MutableLiveData<BaseViewState>) =
        CoroutineExceptionHandler { _, exception ->

            val caughtException: ApplicationRunTimeException

            if (exception is ApplicationRunTimeException) {
                caughtException = exception
                Timber.tag("ApplicationError")
                    .w(
                        "------ Application Error ------ \nType -> ${caughtException.errorType} \nContent -> ${
                        caughtException.getContentFormatted()
                            .toStringOrNull() ?: "No content found."
                        } \nError message: ${caughtException.errorMessage.toStringOrNull() ?: "No message found."}"
                    )
            } else {
                caughtException = ApplicationRunTimeException(
                    errorType = ErrorType.UnexpectedErrorType,
                    errorMessage = exception.javaClass.name
                ).also { exception.printStackTrace() }
                Timber.tag("UnexpectedError")
                    .e(
                        exception,
                        "Error message: ${caughtException.errorMessage.toStringOrNull() ?: "No trace found."}"
                    )
            }

            internalState.postValue(BaseViewState.Error(caughtException))
        }
}
