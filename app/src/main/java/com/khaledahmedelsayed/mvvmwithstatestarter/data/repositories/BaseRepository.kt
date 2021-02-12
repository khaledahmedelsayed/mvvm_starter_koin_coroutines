package com.khaledahmedelsayed.mvvmwithstatestarter.data.repositories

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.khaledahmedelsayed.mvvmwithstatestarter.common.ApplicationRunTimeException
import com.khaledahmedelsayed.mvvmwithstatestarter.common.helpers.ContextProviders
import com.khaledahmedelsayed.mvvmwithstatestarter.common.Utils
import com.khaledahmedelsayed.mvvmwithstatestarter.common.types.ErrorType
import com.khaledahmedelsayed.mvvmwithstatestarter.data.local.SharedPreferencesUtils
import com.khaledahmedelsayed.mvvmwithstatestarter.data.remote.responses.base.ApiBaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseRepository(
    private val contextProviders: ContextProviders,
    private val sharedPrefs: SharedPreferencesUtils? = null,

    ) : KoinComponent {
    private val gson: Gson by inject()

    // https://arturbosch.github.io/detekt/exceptions.html#toogenericexceptioncaught
    // Suppressed because all exceptions are handled and mapped to ApplicationRunTimeException
    @Suppress("TooGenericExceptionCaught")
    protected suspend fun <T> executeNetworkCall(block: suspend CoroutineScope.() -> Response<T>): T {
        return withContext(contextProviders.io) {
            try {
                if (Utils.isConnectedToNetwork().not())
                    throw ApplicationRunTimeException(
                        ErrorType.Network.ClientSide.NoInternetConnection
                    )
                // execute api call block and handle it's response
                return@withContext handleResponse(block())
            } catch (exception: Exception) {
                // Propagate any exception up to the caller's coroutine scope exception handler
                throw when (exception) {
                    is ApplicationRunTimeException -> exception // Coming from handleResponse()
                    is UnknownHostException -> ApplicationRunTimeException(
                        ErrorType.Network.ClientSide.BadBaseUrl,
                        errorsContent = hashMapOf(Pair("", "Couldn't resolve host - check base url."))
                    )
                    is SocketTimeoutException -> ApplicationRunTimeException(
                        ErrorType.Network.ServerSideError,
                        errorsContent = hashMapOf(Pair("", "Server timed out - try again later."))
                    )
                    else -> ApplicationRunTimeException(
                        ErrorType.UnexpectedErrorType,
                        exception.message
                    )
                }
            }
        }
    }

    private fun <T> handleResponse(response: Response<T>): T {
        with(response) {
            if (isSuccessful)
                return body()!!
            else throw buildBackEndException(
                message(),
                errorBody(),
                when {
                    code() in ErrorType.Network.ServerSideError.codeRange ->
                        ErrorType.Network.ServerSideError
                    ErrorType.Network.ClientSide.contains(code()) ->
                        ErrorType.Network.ClientSide.fromStatusCode(code())!!
                    else -> ErrorType.Network.UnexpectedResponseCode
                }
            )
        }
    }

    private fun buildBackEndException(
        errorMessage: String?,
        errorBody: ResponseBody?,
        errorType: ErrorType,
    ): ApplicationRunTimeException {
        try {
            val responseObject = gson.fromJson(
                errorBody?.string(),
                ApiBaseResponse::class.java
            )

            return ApplicationRunTimeException(
                errorType = errorType,
                errorMessage = responseObject.message,
                errorsContent = responseObject.errors
            )
        } catch (jsonMappingException: JsonSyntaxException) {
            return ApplicationRunTimeException(
                errorType = errorType,
                errorMessage = errorMessage,
                errorsContent = hashMapOf(Pair("", "Couldn't retrieve data from server."))
            )
        }
    }
}
