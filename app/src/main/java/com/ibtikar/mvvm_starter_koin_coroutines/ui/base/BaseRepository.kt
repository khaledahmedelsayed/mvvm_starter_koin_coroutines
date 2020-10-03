package com.ibtikar.mvvm_starter_koin_coroutines.ui.base

import com.ibtikar.mvvm_starter_koin_coroutines.ApplicationRunTimeException
import com.ibtikar.mvvm_starter_koin_coroutines.ErrorType
import com.ibtikar.mvvm_starter_koin_coroutines.utils.Utils
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository(private val contextProviders: ContextProviders) {

    suspend fun <T> suspendingApiCall(block: suspend CoroutineScope.() -> Response<T>): T {
        return withContext(contextProviders.IO) {
            try {
                if (Utils.isConnectedToNetwork().not())
                    throw
                    ApplicationRunTimeException(
                        errorType = ErrorType.Network.NoInternetConnection,
                        errorMessage = "Please check your internet connection"
                    )

                // Make api call and handle its response
                val response = block()
                return@withContext handleResponse(response)

            } catch (exception: Throwable) {
                if (exception is ApplicationRunTimeException)
                    throw exception //Propagate the caught application exception down to coroutineExceptionHandler in BaseViewModel
                else
                    throw ApplicationRunTimeException(
                        errorType = ErrorType.Unexpected,
                        errorMessage = exception.message
                    )
            }
        }
    }

    private fun <T> handleResponse(response: Response<T>): T {
        return when (response.code()) {
            200 -> response.body()!!

            401 ->
                throw ApplicationRunTimeException(
                    errorType = ErrorType.Network.Unauthorized,
                    errorMessage = response.message()
                )
            404 ->
                throw ApplicationRunTimeException(
                    errorType = ErrorType.Network.ResourceNotFound,
                    errorMessage = response.message()
                )

            500 ->
                throw ApplicationRunTimeException(
                    errorType = ErrorType.Network.InternalServerError,
                    errorMessage = response.message()
                )
            else ->
                throw ApplicationRunTimeException(
                    errorType = ErrorType.Network.Unexpected,
                    errorMessage = response.message()
                )

        }
    }

}